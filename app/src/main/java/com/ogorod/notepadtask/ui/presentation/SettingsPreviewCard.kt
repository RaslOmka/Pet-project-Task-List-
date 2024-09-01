package com.ogorod.notepadtask.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ogorod.notepadtask.domain.dto.TaskDTO

@Composable
fun PreviewCardPalette() {
    TaskItem(
        modifier = Modifier,
        taskDTO = TaskDTO(
            null,
            title = "Заголовок",
            text = "Текст",
            favorite = true,
            dateCreate = "1 января 2024 г., 12:30:00",
            delete = false,
            timerDelete = null
        ),
        deleteTaskDTO = {},
        restoreTaskDelete = {},
        changeFavoriteTaskDTO = {},
        getItemTaskDTO = {}
    )
}