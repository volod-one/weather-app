package one.volod.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow
import one.volod.weatherapp.data.local.WeatherItem
import one.volod.weatherapp.domain.model.Coordinates
import one.volod.weatherapp.domain.model.WeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherForCity(city: String): Response<WeatherResponse>

    suspend fun getWeatherForCoordinates(lat: String, lon: String): Response<WeatherResponse>

    fun getAllWeatherItemsDb(): Flow<List<WeatherItem>>

    suspend fun insertWeatherItemDb(weatherItem: WeatherItem)

    suspend fun deleteWeatherItemDb(weatherItem: WeatherItem)

    fun isNetworkAvailable(): Boolean

    fun getCitiesPref(): List<String>

    fun saveCitiesPref(cities: List<String>)

    fun isFirstLaunch(): Boolean

    fun setFirstLaunch()

    fun getCurrentLocation(): Coordinates
}

