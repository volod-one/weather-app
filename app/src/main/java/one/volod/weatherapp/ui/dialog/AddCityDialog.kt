package one.volod.weatherapp.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import one.volod.weatherapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityDialog(
    modifier: Modifier = Modifier,
    inputValue: String,
    isChecked: Boolean,
    isError: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    onInput: (String) -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.add_new_city),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        },
        text = {
            Column {
                if (!isChecked) {
                    Text(text = stringResource(R.string.enter_city_name_or_coordinates_without_commas))

                    Spacer(modifier = Modifier.padding(8.dp))

                    OutlinedTextField(
                        value = inputValue,
                        onValueChange = onInput,
                        isError = isError,
                        placeholder = {
                            Text(text = "35.652832 139.839478")
                        },
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.use_current_location),
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f)
                    )

                    Checkbox(checked = isChecked, onCheckedChange = onCheckedChange)
                }
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onConfirm(inputValue) }) {
                Text(text = stringResource(R.string.add))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 4.dp,
    )
}