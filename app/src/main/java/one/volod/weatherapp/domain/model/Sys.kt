package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param partOfDay Part of the day (n - night, d - day)
 */
data class Sys(
    @SerializedName("pod") val partOfDay: String,
)