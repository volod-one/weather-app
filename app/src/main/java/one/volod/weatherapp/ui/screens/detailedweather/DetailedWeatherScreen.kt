package one.volod.weatherapp.ui.screens.detailedweather

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import one.volod.weatherapp.ui.WeatherEvent
import one.volod.weatherapp.ui.WeatherViewModel
import one.volod.weatherapp.ui.dialog.RemoveConfirmationDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedWeatherScreen(
    viewModel: WeatherViewModel,
    navController: NavController,
) {
    val uiState by viewModel.uiState.collectAsState()

    val item = uiState.selectedWeatherItem ?: run {
        viewModel.eventHandler(WeatherEvent.BackToHome(navController))
        return
    }

    val currentTime = System.currentTimeMillis()
    val currentWeather = item.list.find {
        it.timeData * 1000 > currentTime
    } ?: item.list[0]

    Scaffold(topBar = {
        DetailedWeatherTopBar(title = item.city.name,
            onBackClick = { viewModel.eventHandler(WeatherEvent.BackToHome(navController)) },
            onRemoveClick = { viewModel.eventHandler(WeatherEvent.OpenRemoveWeatherItemDialog) })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            DetailedWeatherItemHeader(currWeather = currentWeather)

            DetailedWeatherHourlyInfo(weatherList = item.list)
        }
    }

    if (uiState.isShowingRemoveDialog) {
        RemoveConfirmationDialog(onConfirm = {
            viewModel.eventHandler(
                WeatherEvent.RemoveWeatherItem(
                    item, navController
                )
            )
        }, onDismiss = { viewModel.eventHandler(WeatherEvent.CloseRemoveWeatherItemDialog) })
    }
    BackHandler {
        viewModel.eventHandler(WeatherEvent.BackToHome(navController))
    }
}





