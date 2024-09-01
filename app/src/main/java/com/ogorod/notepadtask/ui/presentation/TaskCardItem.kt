package com.ogorod.notepadtask.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.ui_components.MainAlertDialog

@Composable
fun TaskItem(
    modifier: Modifier,
    taskDTO: TaskDTO,
    deleteTaskDTO: (TaskDTO) -> Unit,
    changeFavoriteTaskDTO: (TaskDTO) -> Unit,
    restoreTaskDelete: (TaskDTO) -> Unit,
    getItemTaskDTO: (TaskDTO) -> Unit
) {
    val buttons = stringArrayResource(id = R.array.alert_dialog_button)
    val text = stringArrayResource(id = R.array.alert_dialog_message_for_task)

    val dialogButtons = remember {
        mutableStateOf("")
    }
    val dialogText = remember {
        mutableStateOf("")
    }

    val openAlertDialog = rememberSaveable {
        mutableStateOf(false)
    }

    if (openAlertDialog.value) {
        MainAlertDialog(
            textDialog = dialogText.value,
            textButton = dialogButtons.value,
            closeDialog = { openAlertDialog.value = false },
            confirmButton = {
                when (dialogText.value) {
                    in text[0]..text[1] -> {
                        deleteTaskDTO(taskDTO)
                    }

                    text[2] -> {
                        restoreTaskDelete(taskDTO)
                    }
                }
            }
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { getItemTaskDTO(taskDTO) }
            .padding(4.dp)
            .alpha(0.9f),
        shape = NotepadTaskTheme.customShapes.cornerCard,
        colors = CardDefaults.cardColors(
            containerColor = NotepadTaskTheme.customColor.surface,
            contentColor = NotepadTaskTheme.customColor.onSurface
        ),
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //дата создания таски
                Text(
                    text = taskDTO.dateCreate,
                    modifier = modifier.padding(start = 4.dp),
                    style = NotepadTaskTheme.customTypography.timeSize
                )
                Row {
                    //добавление в  избранное
                    IconButton(onClick = { changeFavoriteTaskDTO(taskDTO) }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "task_add_favorite",
                            /**
                             * узгнать  за метод - drawBehind
                             */
//                            modifier.drawBehind { drawRect(if (taskDTO.favorite) DarkOrange else Color.Black) },
                            tint = if (taskDTO.favorite) NotepadTaskTheme.customColor.colorFavorite else NotepadTaskTheme.customColor.onSurface
                        )
                    }
                    // кнопка удаления таски
                    IconButton(onClick = {
                        dialogButtons.value = buttons[0]
                        dialogText.value = if (taskDTO.delete) text[1] else text[0]
                        openAlertDialog.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "task_delete",
                        )
                    }
                    if (taskDTO.delete) {
                        // кнопка восстановления таски
                        IconButton(onClick = {
                            dialogText.value = text[2]
                            dialogButtons.value = buttons[1]
                            openAlertDialog.value = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel_delete),
                                contentDescription = "task_delete",
                            )
                        }
                    }

                }
            }

            //заголовок таски
            Text(
                text = taskDTO.title,
                modifier = modifier
                    .padding(start = 12.dp, end = 8.dp)
                    .offset(y = (-10).dp),
                style = NotepadTaskTheme.customTypography.title,
                fontWeight = FontWeight.Medium

            )
            //текст таски
            Text(
                text = taskDTO.text,
                modifier = modifier
                    .padding(horizontal = 4.dp)
                    .offset(y = (-5).dp),
                maxLines = 3,
                style = NotepadTaskTheme.customTypography.body,
                color = NotepadTaskTheme.customColor.outline

            )
        }
    }
}

