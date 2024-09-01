package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(
    navigateToTaskRemoteScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                style = NotepadTaskTheme.customTypography.title
            )
        },
        //вызов контекстного меню с корзиной
        actions = {
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more_vert")
            }
            SettingsDropdownMenu(
                expanded = expanded,
                navigateToTaskRemoteScreen = navigateToTaskRemoteScreen,
                dismiss = { expanded.value = false }
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = NotepadTaskTheme.customColor.tolBar,
            titleContentColor = NotepadTaskTheme.customColor.onSurface,
            navigationIconContentColor = NotepadTaskTheme.customColor.onSurface,
            actionIconContentColor = NotepadTaskTheme.customColor.onSurface
        ),
        //кнопка назад
        navigationIcon = {
            IconButton(onClick = {
                navigateBack()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = "back"
                )
            }
        }
    )
}