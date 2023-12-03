package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param lat Geographical coordinates of the location (latitude)
 * @param lon Geographical coordinates of the location (longitude)
 */
data class Coordinates(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
)