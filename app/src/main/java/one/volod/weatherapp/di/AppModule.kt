package one.volod.weatherapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import one.volod.weatherapp.data.local.WeatherDatabase
import one.volod.weatherapp.data.local.WeatherSharedPref
import one.volod.weatherapp.data.remote.WeatherApi
import one.volod.weatherapp.data.repository.WeatherRepositoryImpl
import one.volod.weatherapp.domain.repository.WeatherRepository
import one.volod.weatherapp.util.LocationUtils
import one.volod.weatherapp.util.NetworkUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherDatabase(
        @ApplicationContext context: Context,
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context, WeatherDatabase::class.java, "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherApi: WeatherApi,
        database: WeatherDatabase,
        @ApplicationContext context: Context,
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            weatherApiService = weatherApi,
            weatherDatabase = database,
            networkUtils = NetworkUtils,
            sharedPref = WeatherSharedPref(context),
            locationUtils = LocationUtils(context),
            context = context
        )
    }
}