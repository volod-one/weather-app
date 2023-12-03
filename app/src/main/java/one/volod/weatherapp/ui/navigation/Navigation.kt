package one.volod.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import one.volod.weatherapp.ui.WeatherViewModel
import one.volod.weatherapp.ui.screens.detailedweather.DetailedWeatherScreen
import one.volod.weatherapp.ui.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DetailedWeather : Screen("detailed_weather")
}

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: WeatherViewModel,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }

        composable(Screen.DetailedWeather.route) {
            DetailedWeatherScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }
    }
}