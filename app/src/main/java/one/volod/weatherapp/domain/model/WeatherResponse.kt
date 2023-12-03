package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param cod Internal parameter
 * @param message Internal parameter
 * @param cnt Number of lines returned by this API call
 * @param list Weather forecast
 * @param city City information
 */
data class WeatherResponse(
    @SerializedName("cod") val cod: String,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<WeatherInfo>,
    @SerializedName("city") val city: City,
)