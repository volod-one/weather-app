package one.volod.weatherapp.ui

import androidx.navigation.NavController
import one.volod.weatherapp.data.local.WeatherItem

sealed class WeatherEvent {
    object LoadWeather : WeatherEvent()
    data class ClickOnWeatherItem(val item: WeatherItem, val navController: NavController) :
        WeatherEvent()

    object OpenAddWeatherItemDialog : WeatherEvent()
    object CloseAddWeatherItemDialog : WeatherEvent()

    data class InputWeatherItem(val input: String) : WeatherEvent()
    data class AddWeatherItem(val location: String) : WeatherEvent()
    object OpenRemoveWeatherItemDialog : WeatherEvent()
    object CloseRemoveWeatherItemDialog : WeatherEvent()
    data class RemoveWeatherItem(val item: WeatherItem, val navController: NavController) :
        WeatherEvent()

    data class BackToHome(val navController: NavController) : WeatherEvent()

    object ToggleWeatherItemCurrentLocationSelected : WeatherEvent()
}