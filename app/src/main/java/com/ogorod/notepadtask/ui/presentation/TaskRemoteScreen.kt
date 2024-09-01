package com.ogorod.notepadtask.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.ui_components.MainAlertDialog

@Composable
fun TaskRemoteScreen(
    modifier: Modifier,
    listTaskDTO: State<List<TaskDTO>>,
    deleteAllTask: (tasksDTO: List<TaskDTO>) -> Unit,
    deleteTask: (TaskDTO) -> Unit,
    restoreAllTasks: (tasksDTO: List<TaskDTO>) -> Unit,
    restoreTask: (tasksDTO: TaskDTO) -> Unit,
    getItemTaskDTO: (taskDTO: TaskDTO) -> Unit,
    navigateBack: () -> Unit,
    cancelJobListDelete: () -> Unit
) {
    val buttons = stringArrayResource(id = R.array.alert_dialog_button)
    val text = stringArrayResource(id = R.array.alert_dialog_message_for_remote_sreen)

    val dialogButtons = remember {
        mutableStateOf("")
    }
    val dialogText = remember {
        mutableStateOf("")
    }

    val openDialog = remember {
        mutableStateOf(false)
    }
    Dialog(onDismissRequest = {
        navigateBack()
        cancelJobListDelete()
    }) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .background(NotepadTaskTheme.customColor.background)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(NotepadTaskTheme.customColor.onBackground),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.padding(8.dp),
                        text = if (listTaskDTO.value.size > 1000_000) stringResource(id = R.string.number_objects_1000000) else
                            stringResource(id = R.string.number_objects) + "${listTaskDTO.value.size}",
                        style = NotepadTaskTheme.customTypography.body,
                        color = NotepadTaskTheme.customColor.onSurface
                    )
                    //кнопка востановить все
                    Row {
                        IconButton(
                            onClick = {
                                dialogButtons.value = buttons[1]
                                dialogText.value = text[1]
                                openDialog.value = true
                            },
                            enabled = listTaskDTO.value.isNotEmpty()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel_delete),
                                contentDescription = "cancel_delete",
                                tint = NotepadTaskTheme.customColor.onSurface
                            )
                        }
                        //кнопка удалить все
                        IconButton(
                            onClick = {
                                dialogButtons.value = buttons[0]
                                dialogText.value = text[0]
                                openDialog.value = true
                            },
                            enabled = listTaskDTO.value.isNotEmpty()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete, contentDescription = "delte",
                                tint = NotepadTaskTheme.customColor.onSurface
                            )
                        }
                        //закрыть корзину
                        IconButton(
                            onClick = {
                                cancelJobListDelete()
                                navigateBack()
                            },
                        )
                        {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "close",
                                tint = NotepadTaskTheme.customColor.colorFavorite
                            )
                        }
                    }
                }
                if (openDialog.value) {
                    MainAlertDialog(
                        textDialog = dialogText.value,
                        textButton = dialogButtons.value,
                        closeDialog = { openDialog.value = false },
                        confirmButton = {
                            when (dialogText.value) {
                                text[0] -> {
                                    deleteAllTask(listTaskDTO.value)
                                }

                                text[1] -> {
                                    restoreAllTasks(listTaskDTO.value)
                                }
                            }
                        }
                    )
                }

                LazyColumn(contentPadding = PaddingValues()) {
                    items(
                        items = listTaskDTO.value,
                        key = { it.id.hashCode() }
                    ) { taskDTO ->
                        TaskItem(
                            modifier = modifier,
                            taskDTO = taskDTO,
                            deleteTaskDTO = { deleteTask(it) },
                            changeFavoriteTaskDTO = { },
                            restoreTaskDelete = { restoreTask(it) },
                            getItemTaskDTO = { getItemTaskDTO(it) }
                        )
                    }
                }
            }
        }
    }
}

