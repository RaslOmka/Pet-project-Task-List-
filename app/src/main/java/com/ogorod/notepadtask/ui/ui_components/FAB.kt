package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@Composable
fun Fab(createNewTask: () -> Unit) {
    FloatingActionButton(
        onClick = {
            createNewTask()
        },
        containerColor = NotepadTaskTheme.customColor.tertiary,
        contentColor = NotepadTaskTheme.customColor.outline,
        shape = NotepadTaskTheme.customShapes.cornerFAB
    ) {


        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
    }
}
