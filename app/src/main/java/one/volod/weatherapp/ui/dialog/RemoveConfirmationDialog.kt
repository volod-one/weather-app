package one.volod.weatherapp.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import one.volod.weatherapp.R

@Composable
fun RemoveConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(R.string.remove))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(R.string.cancel))

            }
        },
        title = { Text(text = stringResource(R.string.remove_city)) },
        text = { Text(text = stringResource(R.string.are_you_sure_you_want_to_remove_this_city)) },
    )
}