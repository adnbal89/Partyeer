package com.partyeer.presentation.ui.main.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.partyeer.presentation.R


@Composable
fun ProgressDialog(show: State<Boolean?>) {
    if (show.value == true) {
        Dialog(
            onDismissRequest = {},
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
            content = {
                CircularProgressIndicator()
            }
        )
    }
}

@Composable
fun ErrorDialog(message: State<String?>) {
    val show = remember {
        mutableStateOf(true)
    }
    if (show.value && message.value.isNullOrEmpty().not()) {
        AlertDialog(
            title = { Text(text = stringResource(id = R.string.error)) },
            text = { Text(text = message.value.orEmpty()) },
            confirmButton = {
                TextButton(onClick = { show.value = false }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            onDismissRequest = {}
        )
    }
}

