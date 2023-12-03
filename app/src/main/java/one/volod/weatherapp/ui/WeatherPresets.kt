package one.volod.weatherapp.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import one.volod.weatherapp.R

enum class WeatherPresets(@DrawableRes val icon: Int, @StringRes val description: Int) {
    CLEAR_SKY_DAY(R.drawable.d01, R.string.clear_sky),
    FEW_CLOUDS_DAY(R.drawable.d02, R.string.few_clouds),
    SCATTERED_CLOUDS_DAY(R.drawable.d03, R.string.scattered_clouds),
    BROKEN_CLOUDS_DAY(R.drawable.d04, R.string.broken_clouds),
    SHOWER_RAIN_DAY(R.drawable.d09, R.string.shower_rain),
    RAIN_DAY(R.drawable.d10, R.string.rain),
    THUNDERSTORM_DAY(R.drawable.d11, R.string.thunderstorm),
    SNOW_DAY(R.drawable.d13, R.string.snow),
    MIST_DAY(R.drawable.d50, R.string.mist),
}
