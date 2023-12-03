package one.volod.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import one.volod.weatherapp.domain.model.City

@Dao
interface WeatherDao {

    @Insert(entity = WeatherItem::class, onConflict = REPLACE)
    suspend fun insert(weatherItem: WeatherItem)

    @Delete
    suspend fun delete(weatherItem: WeatherItem)

    @Query("SELECT * FROM WeatherItem ORDER BY city DESC")
    fun getAll(): Flow<List<WeatherItem>>
}