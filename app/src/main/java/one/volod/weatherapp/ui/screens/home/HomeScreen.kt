package one.volod.weatherapp.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import one.volod.weatherapp.R
import one.volod.weatherapp.ui.WeatherEvent
import one.volod.weatherapp.ui.WeatherViewModel
import one.volod.weatherapp.ui.dialog.AddCityDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: WeatherViewModel,
    navController: NavController,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.eventHandler(WeatherEvent.OpenAddWeatherItemDialog)
        }) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.add_new_city)
            )
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.isLoadingError) {
                HomeWeatherLoadingError(onRetryClick = {
                    viewModel.eventHandler(WeatherEvent.LoadWeather)
                })
            }

            if (uiState.isLoading) {
                HomeWeatherLoading()
            } else {
                HomeWeatherItemList(
                    weatherList = uiState.citiesList,
                    onItemClick = {
                        viewModel.eventHandler(
                            WeatherEvent.ClickOnWeatherItem(it, navController)
                        )
                    }
                )
            }
        }

        if (uiState.isShowingAddDialog) {
            AddCityDialog(
                inputValue = uiState.inputValue,
                isChecked = uiState.isInputCurrentLocationSelected,
                isError = uiState.isInputError,
                onCheckedChange = {
                    viewModel.eventHandler(WeatherEvent.ToggleWeatherItemCurrentLocationSelected)
                },
                onDismiss = {
                    viewModel.eventHandler(WeatherEvent.CloseAddWeatherItemDialog)
                },
                onConfirm = {
                    viewModel.eventHandler(WeatherEvent.AddWeatherItem(it))
                },
                onInput = {
                    viewModel.eventHandler(WeatherEvent.InputWeatherItem(it))
                }
            )
        }
    }
}







