package one.volod.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import one.volod.weatherapp.data.local.WeatherItem
import one.volod.weatherapp.domain.repository.WeatherRepository
import one.volod.weatherapp.ui.navigation.Screen
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val defaultCitiesList = listOf("Tokyo", "Hyogo", "Oita", "Hokkaido")

    init {
        if (repository.isFirstLaunch()) {
            repository.saveCitiesPref(defaultCitiesList)
            repository.setFirstLaunch()
        }

        checkNetwork()

        if (_uiState.value.isNetworkAvailable.not()) {
            getAllWeatherFromDB()
        } else {
            getAllWeather()
        }
    }

    fun eventHandler(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.ClickOnWeatherItem -> selectWeatherItem(event.item, event.navController)
            is WeatherEvent.BackToHome -> backToHome(event.navController)
            is WeatherEvent.RemoveWeatherItem -> removeWeatherItem(event.item, event.navController)
            is WeatherEvent.OpenRemoveWeatherItemDialog -> showRemoveDialog()
            is WeatherEvent.CloseRemoveWeatherItemDialog -> hideRemoveDialog()
            is WeatherEvent.AddWeatherItem -> addWeatherItem(event.location)
            is WeatherEvent.CloseAddWeatherItemDialog -> closeAddDialog()
            is WeatherEvent.OpenAddWeatherItemDialog -> showAddDialog()
            is WeatherEvent.LoadWeather -> getAllWeather()
            is WeatherEvent.ToggleWeatherItemCurrentLocationSelected -> {
                _uiState.value = uiState.value.copy(
                    isInputCurrentLocationSelected = !uiState.value.isInputCurrentLocationSelected
                )
            }

            is WeatherEvent.InputWeatherItem -> {
                _uiState.value = uiState.value.copy(
                    inputValue = event.input
                )
            }
        }
    }

    private fun getWeather(vararg location: String) {
        viewModelScope.launch {
            try {
                val res = if (location.size == 1) {
                    repository.getWeatherForCity(location[0])
                } else {
                    repository.getWeatherForCoordinates(location[0], location[1])
                }
                if (res.isSuccessful) {
                    res.body()?.let {
                        val weatherItem = WeatherItem(
                            city = it.city, list = it.list
                        )

                        repository.insertWeatherItemDb(weatherItem)
                    }
                } else {
                    _uiState.value = uiState.value.copy(
                        isInputError = true
                    )
                }
            } catch (e: IOException) {
                _uiState.value = uiState.value.copy(
                    isLoadingError = true
                )
                e.printStackTrace()
            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(
                    isLoadingError = true
                )
                e.printStackTrace()
            }
        }

    }

    private fun getAllWeather() {
        val citiesList = repository.getCitiesPref()
        _uiState.value = uiState.value.copy(
            isLoading = true,
            isInputError = false,
        )
        for (city in citiesList) {
            getWeather(city)
        }
        getAllWeatherFromDB()
        _uiState.value = uiState.value.copy(
            isLoading = false
        )
    }

    private fun getAllWeatherFromDB() {
        viewModelScope.launch {
            repository.getAllWeatherItemsDb().collectLatest {
                _uiState.value = uiState.value.copy(
                    citiesList = it
                )
            }
        }
    }

    private fun selectWeatherItem(weatherItem: WeatherItem, navController: NavController) {
        _uiState.value = uiState.value.copy(
            selectedWeatherItem = weatherItem
        )
        navController.navigate(Screen.DetailedWeather.route)
    }

    private fun backToHome(navController: NavController) {
        uiState.value.selectedWeatherItem?.let {
            navController.popBackStack()
        } ?: return
    }

    private fun removeWeatherItem(weatherItem: WeatherItem, navController: NavController) {
        navController.popBackStack()
        _uiState.value = uiState.value.copy(selectedWeatherItem = null,
            isShowingRemoveDialog = false,
            citiesList = uiState.value.citiesList.filter { it.city.name != weatherItem.city.name })

        viewModelScope.launch {
            repository.deleteWeatherItemDb(weatherItem)
        }

        repository.saveCitiesPref(uiState.value.citiesList.map { it.city.name })
    }

    private fun addWeatherItem(rawLocation: String) {
        if (uiState.value.isInputCurrentLocationSelected) {
            val coordinates = try {
                repository.getCurrentLocation()
            } catch (e: Exception) {
                setInputError()
                return
            }
            getWeather(coordinates.lat.toString(), coordinates.lon.toString())
        } else {
            when (validateLocation(rawLocation)) {
                LocationType.COORDINATES -> {
                    val (lat, lon) = rawLocation.split(" ")
                    getWeather(lat, lon)
                }

                LocationType.CITY_NAME -> getWeather(rawLocation)
                LocationType.EMPTY -> setInputError()
            }
        }

        if (uiState.value.isInputError) return

        _uiState.value = uiState.value.copy(
            isShowingAddDialog = false,
            isInputError = false,
            inputValue = ""
        )

        repository.saveCitiesPref(uiState.value.citiesList.map { it.city.name })

        getAllWeatherFromDB()
    }

    private fun checkNetwork() {
        _uiState.value = uiState.value.copy(
            isNetworkAvailable = repository.isNetworkAvailable()
        )
    }

    private fun showRemoveDialog() {
        _uiState.value = uiState.value.copy(
            isShowingRemoveDialog = true
        )
    }

    private fun hideRemoveDialog() {
        _uiState.value = uiState.value.copy(
            isShowingRemoveDialog = false
        )
    }

    private fun showAddDialog() {
        _uiState.value = uiState.value.copy(
            isShowingAddDialog = true
        )
    }

    private fun closeAddDialog() {
        _uiState.value = uiState.value.copy(
            isShowingAddDialog = false, isInputError = false
        )
    }

    private fun validateLocation(location: String): LocationType {
        val coordinatesRegex = """^-?(\d+\.\d+)\s(-?\d+\.\d+)$""".toRegex()

        return when {
            location.matches(coordinatesRegex) -> LocationType.COORDINATES
            "" == location -> LocationType.EMPTY
            else -> LocationType.CITY_NAME
        }
    }

    private fun setInputError() {
        _uiState.value = uiState.value.copy(
            isInputError = true
        )
    }

    enum class LocationType {
        COORDINATES, CITY_NAME, EMPTY
    }
}
