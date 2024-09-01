package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@Composable
fun MainAlertDialog(
    textDialog: String,
    textButton: String,
    closeDialog: () -> Unit,
    confirmButton: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { closeDialog() },
        dismissButton = {
            TextButton(
                onClick = { closeDialog() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = NotepadTaskTheme.customColor.transparent,
                    contentColor = NotepadTaskTheme.customColor.outline
                ),
                border = BorderStroke(1.dp, NotepadTaskTheme.customColor.outline)
            ) {
                Text(
                    text = stringResource(id = R.string.alert_dialog_button_cancel),
                    style = NotepadTaskTheme.customTypography.label,
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    closeDialog()
                    confirmButton()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = NotepadTaskTheme.customColor.transparent,
                    contentColor = NotepadTaskTheme.customColor.colorFavorite
                ),
                border = BorderStroke(1.dp, NotepadTaskTheme.customColor.colorFavorite)
            ) {
                Text(
                    text = textButton,
                    style = NotepadTaskTheme.customTypography.label
                )
            }
        },

        title = {
            Text(
                text = stringResource(id = R.string.alert_dialog_title),
                style = NotepadTaskTheme.customTypography.title
            )
        },
        text = {
            Text(
                text = textDialog,
                style = NotepadTaskTheme.customTypography.body,
            )
        },
        shape = NotepadTaskTheme.customShapes.cornerCard,
        containerColor = NotepadTaskTheme.customColor.background,
        titleContentColor = NotepadTaskTheme.customColor.colorFavorite,
        textContentColor = NotepadTaskTheme.customColor.onSurface
    )
}