package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param id Weather condition id
 * @param main Group of weather parameters (Rain, Snow, Extreme etc.)
 * @param description Weather condition within the group
 * @param icon Weather icon id
 */
data class Weather(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String,
)