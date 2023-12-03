package one.volod.weatherapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import one.volod.weatherapp.domain.model.WeatherInfo

class WeatherInfoTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<WeatherInfo> {
        return Gson().fromJson(value, Array<WeatherInfo>::class.java).toList()
    }

    @TypeConverter
    fun fromList(list: List<WeatherInfo>): String {
        return Gson().toJson(list)
    }
}