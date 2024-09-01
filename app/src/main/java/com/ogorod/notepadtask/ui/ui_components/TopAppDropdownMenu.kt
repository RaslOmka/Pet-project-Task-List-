package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@Composable
fun SettingsDropdownMenu(
    expanded: MutableState<Boolean>,
    navigateToTaskRemoteScreen: () -> Unit,
    dismiss: () -> Unit
) {
    Box(modifier = Modifier.offset(y = 16.dp)) {
        DropdownMenu(
            expanded = expanded.value, onDismissRequest = { dismiss() },
            modifier = Modifier.background(NotepadTaskTheme.customColor.onBackground)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = R.string.basket),
                        color = NotepadTaskTheme.customColor.onSurface
                    )
                },
                onClick = {
                    dismiss()
                    navigateToTaskRemoteScreen()
                },
            )
        }
    }
}