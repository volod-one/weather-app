package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param speed Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 * @param deg Wind direction, degrees (meteorological)
 * @param gust Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
 */
data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val deg: Int,
    @SerializedName("gust") val gust: Double,
)