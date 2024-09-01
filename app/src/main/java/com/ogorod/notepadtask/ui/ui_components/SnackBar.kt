package com.ogorod.notepadtask.ui.ui_components

import android.os.CountDownTimer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@Composable
fun MainSnackBar(snackBarData: SnackbarData) {
    val timer = rememberSaveable {
        mutableStateOf("")
    }

    val countDownTimer = object : CountDownTimer(3500, 1000) {
        override fun onTick(p0: Long) {
            timer.value = "${p0 / 1000}"
        }

        override fun onFinish() {
            this.cancel()
            snackBarData.performAction()
        }
    }.start()

    Snackbar(
        modifier = Modifier.padding(horizontal = 6.dp),
        action = {
            Text(text = timer.value)
        },
        dismissAction = {
            TextButton(onClick = {
                countDownTimer.cancel()
                snackBarData.dismiss()
            }) {
                Text(
                    text = stringResource(id = R.string.snackbar_dismiss_text),
                    color = NotepadTaskTheme.customColor.outline
                )
            }
        },
        shape = NotepadTaskTheme.customShapes.cornerCard,
        containerColor = NotepadTaskTheme.customColor.background,
        contentColor = NotepadTaskTheme.customColor.outline,
        actionContentColor = NotepadTaskTheme.customColor.colorFavorite
    ) {
        Text(text = stringResource(id = R.string.snackbar_message_text))
    }
}