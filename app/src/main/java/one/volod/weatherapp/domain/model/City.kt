package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param id City ID
 * @param name City name
 * @param coordinates City geo location
 * @param country Country code (GB, JP etc.)
 * @param population City population
 * @param timezone Shift in seconds from UTC
 * @param sunrise Sunrise time, unix, UTC
 * @param sunset Sunset time, unix, UTC
 */
data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int,
)