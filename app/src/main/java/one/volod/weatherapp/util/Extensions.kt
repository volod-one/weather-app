package one.volod.weatherapp.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import one.volod.weatherapp.ui.WeatherPresets
import java.util.Date

fun Long.toStringDayTime(): String {
    return Date(this).toString().substring(0, 3) + ", " + Date(this).toString().substring(11, 16)
}


fun Context.hasLocationPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

fun String.getWeatherIcon(): Int {
    return when (this) {
        "01d", "01n" -> WeatherPresets.CLEAR_SKY_DAY.icon
        "02d", "02n" -> WeatherPresets.FEW_CLOUDS_DAY.icon
        "03d", "03n" -> WeatherPresets.SCATTERED_CLOUDS_DAY.icon
        "04d", "04n" -> WeatherPresets.BROKEN_CLOUDS_DAY.icon
        "09d", "09n" -> WeatherPresets.SHOWER_RAIN_DAY.icon
        "10d", "10n" -> WeatherPresets.RAIN_DAY.icon
        "11d", "11n" -> WeatherPresets.THUNDERSTORM_DAY.icon
        "13d", "13n" -> WeatherPresets.SNOW_DAY.icon
        "50d", "50n" -> WeatherPresets.MIST_DAY.icon
        else -> WeatherPresets.CLEAR_SKY_DAY.icon
    }
}

fun String.getWeatherDescription(): Int {
    return when (this) {
        "01d", "01n" -> WeatherPresets.CLEAR_SKY_DAY.description
        "02d", "02n" -> WeatherPresets.FEW_CLOUDS_DAY.description
        "03d", "03n" -> WeatherPresets.SCATTERED_CLOUDS_DAY.description
        "04d", "04n" -> WeatherPresets.BROKEN_CLOUDS_DAY.description
        "09d", "09n" -> WeatherPresets.SHOWER_RAIN_DAY.description
        "10d", "10n" -> WeatherPresets.RAIN_DAY.description
        "11d", "11n" -> WeatherPresets.THUNDERSTORM_DAY.description
        "13d", "13n" -> WeatherPresets.SNOW_DAY.description
        "50d", "50n" -> WeatherPresets.MIST_DAY.description
        else -> WeatherPresets.CLEAR_SKY_DAY.description
    }
}
