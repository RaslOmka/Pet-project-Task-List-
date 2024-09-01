package com.ogorod.notepadtask.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.theme.ThemeModeSelection

@Composable
fun SettingsDialogTheme(
    modifier: Modifier,
    themeModeIndex: Int,
    setThemMode: (ThemeModeSelection) -> Unit,
    navigateBack: () -> Unit
) {

    // список значений для выбора темы
    val listThemeMode = stringArrayResource(id = R.array.array_theme_mode)
    val listOptionsThemeMode = remember {
        mutableStateOf(listThemeMode)
    }
    //выбранное значение темы
    val (selected, onSelected) = remember {
        mutableStateOf(listOptionsThemeMode.value[themeModeIndex])
    }

    val tempThemeMode = rememberSaveable {
        mutableStateOf(ThemeModeSelection.values()[themeModeIndex])
    }

    Dialog(onDismissRequest = {
        setThemMode(tempThemeMode.value)
        navigateBack()
    }) {
        Card(
            shape = NotepadTaskTheme.customShapes.cornerCard,
            colors = CardDefaults.cardColors(
                containerColor = NotepadTaskTheme.customColor.background,
                contentColor = NotepadTaskTheme.customColor.onSurface
            )
        ) {
            Column(
                modifier = modifier
                    .selectableGroup()
                    .padding(vertical = 16.dp)
            ) {
                listOptionsThemeMode.value.forEach {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(color = if (it == selected) NotepadTaskTheme.customColor.onBackground else NotepadTaskTheme.customColor.transparent)
                            .clickable(enabled = (it != selected)) {
                                onSelected(it)
                                setThemMode(
                                    when (it) {
                                        listOptionsThemeMode.value[0] -> ThemeModeSelection.System
                                        listOptionsThemeMode.value[1] -> ThemeModeSelection.Light
                                        else -> ThemeModeSelection.Dark
                                    }
                                )
                            }) {
                        RadioButton(
                            selected = (it == selected),
                            onClick = {
                                onSelected(it)
                                setThemMode(
                                    when (it) {
                                        listOptionsThemeMode.value[0] -> ThemeModeSelection.System
                                        listOptionsThemeMode.value[1] -> ThemeModeSelection.Light
                                        else -> ThemeModeSelection.Dark
                                    }
                                )
                            },
                            enabled = (it != selected),
                            colors = RadioButtonDefaults.colors(
                                unselectedColor = NotepadTaskTheme.customColor.onSurface,
                                disabledSelectedColor = NotepadTaskTheme.customColor.colorFavorite
                            )
                        )
                        Text(
                            it,
                            fontSize = 28.sp,
                            modifier = modifier.padding(4.dp),
                            style = NotepadTaskTheme.customTypography.body
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    // отменить применение темы
                    TextButton(
                        onClick = {
                            setThemMode(tempThemeMode.value)
                            navigateBack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Unspecified,
                            contentColor = NotepadTaskTheme.customColor.onSurface
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.dialog_selection_cancel),
                            style = NotepadTaskTheme.customTypography.label
                        )
                    }
                    // применить тему
                    TextButton(
                        onClick = {
                            navigateBack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Unspecified,
                            disabledContainerColor = Color.Unspecified,
                            contentColor = NotepadTaskTheme.customColor.colorFavorite,
                            disabledContentColor = NotepadTaskTheme.customColor.colorFavorite.copy(alpha = 0.5f)
                        ),
                        enabled = (listOptionsThemeMode.value[tempThemeMode.value.ordinal] != selected)
                    ) {
                        Text(
                            text = stringResource(id = R.string.dialog_selection_accept),
                            style = NotepadTaskTheme.customTypography.label
                        )
                    }
                }
            }
        }
    }
}