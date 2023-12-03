package one.volod.weatherapp.data.repository

import android.content.Context
import one.volod.weatherapp.data.local.WeatherDatabase
import one.volod.weatherapp.data.local.WeatherItem
import one.volod.weatherapp.data.local.WeatherSharedPref
import one.volod.weatherapp.data.remote.WeatherApi
import one.volod.weatherapp.domain.repository.WeatherRepository
import one.volod.weatherapp.util.LocationUtils
import one.volod.weatherapp.util.NetworkUtils

class WeatherRepositoryImpl(
    private val weatherApiService: WeatherApi,
    private val weatherDatabase: WeatherDatabase,
    private val networkUtils: NetworkUtils,
    private val locationUtils: LocationUtils,
    private val sharedPref: WeatherSharedPref,
    private val context: Context
) : WeatherRepository {
    override suspend fun getWeatherForCity(city: String) =
        weatherApiService.getWeatherForecast(city = city)

    override suspend fun getWeatherForCoordinates(lat: String, lon: String) =
        weatherApiService.getWeatherForecastByCoordinates(lat = lat, lon = lon)

    override fun getAllWeatherItemsDb() = weatherDatabase.weatherDao.getAll()

    override suspend fun insertWeatherItemDb(weatherItem: WeatherItem) =
        weatherDatabase.weatherDao.insert(weatherItem)

    override suspend fun deleteWeatherItemDb(weatherItem: WeatherItem) =
        weatherDatabase.weatherDao.delete(weatherItem)

    override fun isNetworkAvailable() = networkUtils.isNetworkAvailable(context)

    override fun getCitiesPref() = sharedPref.getCities()

    override fun saveCitiesPref(cities: List<String>) = sharedPref.saveCities(cities)

    override fun isFirstLaunch() = sharedPref.isFirstLaunch()

    override fun setFirstLaunch() = sharedPref.setFirstLaunch()

    override fun getCurrentLocation() = locationUtils.getCurrentLocation()
}