package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param h Rain volume for the last 3 hours
 */
data class Rain(
    @SerializedName("h") val h: Double,
)