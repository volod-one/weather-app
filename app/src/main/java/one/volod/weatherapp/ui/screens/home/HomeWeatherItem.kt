package one.volod.weatherapp.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import one.volod.weatherapp.R

@Composable
fun HomeWeatherItem(
    cityName: String,
    currentTemperature: String,
    @StringRes currentWeatherDescription: Int,
    @DrawableRes weatherIcon: Int,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onItemClick()
        }) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = weatherIcon),
                    contentDescription = stringResource(R.string.weather_icon),
                    modifier = Modifier.size(64.dp),
                )

                Text(
                    text = "$currentTemperature°C",
                    fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cityName,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                    fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = stringResource(id = currentWeatherDescription),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                    textAlign = TextAlign.Center,
                )

            }
        }
    }
}
