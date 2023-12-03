package one.volod.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @param temp Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * @param feelsLike Temperature. This temperature parameter accounts for the human perception of weather. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * @param tempMin Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * @param tempMax Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas). Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
 * @param pressure Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
 * @param seaLevel Atmospheric pressure on the sea level, hPa
 * @param groundLevel Atmospheric pressure on the ground level, hPa
 * @param humidity Humidity, %
 * @param tempKf Internal parameter
 */
data class WeatherMain(
    @SerializedName("temp") val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("sea_level") val seaLevel: Int,
    @SerializedName("grnd_level") val groundLevel: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_kf") val tempKf: Double,
)