package one.volod.weatherapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import one.volod.weatherapp.R

@Composable
fun HomeWeatherLoadingError(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp, vertical = 8.dp
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_occurred_while_weather_loading),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    horizontal = 16.dp, vertical = 8.dp
                ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = onRetryClick
        ) {
            Text(
                text = stringResource(R.string.retry),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        horizontal = 16.dp, vertical = 8.dp
                    ),
                textAlign = TextAlign.Center
            )
        }
    }
}