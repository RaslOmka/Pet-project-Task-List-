package com.ogorod.notepadtask.ui.ui_components

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TopAppBarTaskScreen(
    saveTask: () -> Unit,
    restoreTaskDelete: Boolean,
    behavior: TopAppBarScrollBehavior,
    showSaveTask: Boolean,
    navigateBack: () -> Unit,
    changeShowSaveTask: () -> Unit,
) {
    val enabled = remember(showSaveTask) {
        mutableStateOf(showSaveTask)
    }
    //закрыть клавиатуру  и убрать фокус
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TopAppBar(
        title = { Text(text = stringResource(id = R.string.name_title_topAppBar)) },
        navigationIcon = {
            IconButton(onClick = {
                if (showSaveTask) {
                    saveTask()
                }
                navigateBack()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.backspace),
                    contentDescription = "back"
                )
            }
        },

        actions = {
            if (restoreTaskDelete) {
                IconButton(
                    onClick = {
                        navigateBack()
                        saveTask()
                    },
                    enabled = true
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel_delete),
                        contentDescription = "restore",
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        changeShowSaveTask()
                        saveTask()
                    },
                    enabled = enabled.value
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "done",
                    )
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = NotepadTaskTheme.customColor.tolBar,
            titleContentColor = NotepadTaskTheme.customColor.onSurface,
            navigationIconContentColor = NotepadTaskTheme.customColor.onSurface,
            actionIconContentColor = NotepadTaskTheme.customColor.onSurface
        ),
        scrollBehavior = behavior
    )
//переопределить экранную кнопку "НАЗАД"
    BackHandler {
        if (showSaveTask) {
            saveTask()
        }
        navigateBack()
    }
}