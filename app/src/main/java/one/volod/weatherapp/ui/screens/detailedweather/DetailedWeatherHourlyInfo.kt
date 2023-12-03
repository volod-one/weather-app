package one.volod.weatherapp.ui.screens.detailedweather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.volod.weatherapp.R
import one.volod.weatherapp.domain.model.WeatherInfo
import one.volod.weatherapp.util.getWeatherDescription
import one.volod.weatherapp.util.getWeatherIcon
import one.volod.weatherapp.util.toStringDayTime
import kotlin.math.roundToInt

@Composable
fun DetailedWeatherHourlyInfo(
    modifier: Modifier = Modifier,
    weatherList: List<WeatherInfo>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.hourly_info),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                items(weatherList.size) { index ->
                    Row(
                        modifier = Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = (weatherList[index].timeData * 1000).toStringDayTime(),
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(0.3f)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(id = weatherList[index].weather[0].icon.getWeatherIcon()),
                                contentDescription = stringResource(id = R.string.weather_icon),
                                modifier = Modifier.size(32.dp)
                            )

                            Text(text = stringResource(id = weatherList[index].weather[0].icon.getWeatherDescription()))
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Text(
                            text = stringResource(
                                R.string.temp, weatherList[index].main.temp.roundToInt()
                            ),
                        )
                    }

                    if (index < weatherList.lastIndex) {
                        Divider(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                        )
                    }
                }
            }
        }
    }
}