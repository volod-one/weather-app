package one.volod.weatherapp.data.remote

import one.volod.weatherapp.domain.model.WeatherResponse
import one.volod.weatherapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ja",
        @Query("APPID") apiKey: String = Constants.API_KEY,
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getWeatherForecastByCoordinates(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ja",
        @Query("APPID") apiKey: String = Constants.API_KEY,
    ): Response<WeatherResponse>
}
