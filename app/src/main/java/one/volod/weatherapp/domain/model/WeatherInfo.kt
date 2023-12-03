package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param timeData Time of data forecasted, unix, UTC
 * @param main Main information about the weather
 * @param weather Weather condition codes
 * @param clouds Cloudiness, %
 * @param wind Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 * @param visibility Average visibility, metres
 * @param pop Probability of precipitation
 * @param rain Rain volume for last 3 hours, mm
 * @param sys Internal parameter
 * @param timeString Time of data forecasted, ISO, UTC
 */
data class WeatherInfo(
    @SerializedName("dt") val timeData: Long,
    @SerializedName("main") val main: WeatherMain,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("pop") val pop: Double,
    @SerializedName("rain") val rain: Rain,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("dt_txt") val timeString: String,
)

