package one.volod.weatherapp.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import one.volod.weatherapp.data.local.WeatherItem
import one.volod.weatherapp.util.getWeatherDescription
import one.volod.weatherapp.util.getWeatherIcon
import kotlin.math.roundToInt

@Composable
fun HomeWeatherItemList(
    weatherList: List<WeatherItem>,
    modifier: Modifier = Modifier,
    onItemClick: (WeatherItem) -> Unit,
) {
    val currentTime = System.currentTimeMillis()
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding()
    ) {
        items(weatherList.size) { index ->
            val weatherItemForCurrentTime = weatherList[index].list.find {
                it.timeData * 1000 > currentTime
            } ?: weatherList[index].list[0]

            HomeWeatherItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onItemClick = { onItemClick(weatherList[index]) },
                cityName = weatherList[index].city.name,
                currentWeatherDescription = weatherItemForCurrentTime.weather[0].icon.getWeatherDescription(),
                currentTemperature = weatherItemForCurrentTime.main.temp.roundToInt().toString(),
                weatherIcon = weatherItemForCurrentTime.weather[0].icon.getWeatherIcon(),
            )
        }
    }
}