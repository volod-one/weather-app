package one.volod.weatherapp.ui.screens.detailedweather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import kotlin.math.roundToInt

@Composable
fun DetailedWeatherItemHeader(
    currWeather: WeatherInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 32.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = stringResource(R.string.now))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "${currWeather.main.temp.roundToInt()}°",
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                )

                Image(
                    painter = painterResource(id = currWeather.weather[0].icon.getWeatherIcon()),
                    contentDescription = stringResource(id = R.string.weather_icon),
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = stringResource(currWeather.weather[0].icon.getWeatherDescription()))

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                text = stringResource(R.string.feels_like, currWeather.main.feelsLike.roundToInt())
            )
        }
    }
}