package one.volod.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WeatherItem::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    WeatherInfoTypeConverter::class,
    WeatherCityTypeConverter::class,
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}