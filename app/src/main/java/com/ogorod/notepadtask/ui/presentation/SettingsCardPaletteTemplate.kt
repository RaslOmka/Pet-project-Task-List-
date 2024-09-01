package com.ogorod.notepadtask.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.ui.theme.CustomPallet
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@Composable
fun TemplatePalette(
    palette: CustomPallet,
    darkTheme: Boolean,
    paletteCurrent: Int,
    setCustomPallet: (CustomPallet) -> Unit
) {
    val enabled = palette.ordinal == paletteCurrent
    val enabledBorder = remember(enabled) {
        mutableStateOf(enabled)
    }
    val borderColor = remember(darkTheme) {
        mutableStateOf(if (!darkTheme) palette.borderColorLight else palette.borderColorDark)
    }
    val containerColor = remember(darkTheme) {
        mutableStateOf(if (!darkTheme) palette.containerColorLight else palette.containerColorDark)
    }

    Box(
        modifier = Modifier
            .background(
                containerColor.value,
                shape = NotepadTaskTheme.customShapes.cornerCard
            )
            .border(
                2.dp,
                shape = NotepadTaskTheme.customShapes.cornerCard,
                color = if (enabledBorder.value) borderColor.value else Color.Unspecified
            )
            .size(100.dp)
            .clickable(
                enabled = !enabledBorder.value
            ) { setCustomPallet(palette) },
        contentAlignment = Alignment.TopEnd
    ) {
        if (enabledBorder.value) {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 8.dp),
                imageVector = Icons.Default.Check,
                contentDescription = "",
                tint = borderColor.value
            )
        }
    }
}
