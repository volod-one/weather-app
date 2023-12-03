package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param all Cloudiness, %
 */
data class Clouds(
    @SerializedName("all") val all: Int,
)