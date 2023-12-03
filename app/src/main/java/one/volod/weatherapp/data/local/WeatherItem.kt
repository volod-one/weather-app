package one.volod.weatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import one.volod.weatherapp.domain.model.City
import one.volod.weatherapp.domain.model.WeatherInfo

@Entity
data class WeatherItem(
    @PrimaryKey(autoGenerate = false) val city: City,
    val list: List<WeatherInfo>,
    val savedTimestamp: Long = System.currentTimeMillis(),
)
