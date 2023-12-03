package one.volod.weatherapp.ui

import one.volod.weatherapp.data.local.WeatherItem

data class WeatherUiState(
    val citiesList: List<WeatherItem> = listOf(),
    val selectedWeatherItem: WeatherItem? = null,
    val inputValue: String = "",
    val isLoading: Boolean = false,
    val isLoadingError: Boolean = false,
    val isShowingAddDialog: Boolean = false,
    val isShowingRemoveDialog: Boolean = false,
    val isNetworkAvailable: Boolean = false,
    val isInputError: Boolean = false,
    val isInputCurrentLocationSelected: Boolean = false,
)
