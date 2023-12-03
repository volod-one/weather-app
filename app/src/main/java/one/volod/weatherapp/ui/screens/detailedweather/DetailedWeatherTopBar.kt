package one.volod.weatherapp.ui.screens.detailedweather

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import one.volod.weatherapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedWeatherTopBar(
    title: String,
    onBackClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    CenterAlignedTopAppBar(title = {
        Text(text = title)
    }, navigationIcon = {
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }
    }, actions = {
        IconButton(
            onClick = onRemoveClick
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.remove)
            )
        }
    })
}