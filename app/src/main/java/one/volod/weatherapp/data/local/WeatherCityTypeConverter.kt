package one.volod.weatherapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import one.volod.weatherapp.domain.model.City

class WeatherCityTypeConverter {
    @TypeConverter
    fun fromString(value: String): City {
        return Gson().fromJson(value, City::class.java)
    }

    @TypeConverter
    fun fromCity(city: City): String {
        return Gson().toJson(city)
    }
}