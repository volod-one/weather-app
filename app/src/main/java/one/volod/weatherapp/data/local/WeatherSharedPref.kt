package one.volod.weatherapp.data.local

import android.content.Context

class WeatherSharedPref(context: Context) {
    private val sharedPref = context.getSharedPreferences("weather", Context.MODE_PRIVATE)

    fun saveCities(cities: List<String>) {
        val editor = sharedPref.edit()
        editor.putStringSet("cities", cities.toSet())
        editor.apply()
    }

    fun getCities(): List<String> {
        return sharedPref.getStringSet("cities", setOf())?.toList() ?: listOf()
    }

    fun setFirstLaunch() {
        val editor = sharedPref.edit()
        editor.putBoolean("firstLaunch", false)
        editor.apply()
    }

    fun isFirstLaunch(): Boolean {
        return sharedPref.getBoolean("firstLaunch", true)
    }
}