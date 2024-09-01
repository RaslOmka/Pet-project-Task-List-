package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.ui.presentation.TaskItem

@Composable
fun TasksLazyColumn(
    modifier: Modifier,
    customModifier: Modifier,
    paddingValues: PaddingValues,
    stateTaskList: State<List<TaskDTO>>,
    deleteTaskDTO: (taskDto: TaskDTO) -> Unit,
    changeFavoriteTaskDTO: (taskDTO: TaskDTO) -> Unit,
    getItemTaskDTO: (taskDTO: TaskDTO) -> Unit
) {

    LazyColumn(
        contentPadding = paddingValues,
        modifier = customModifier
    ) {
        items(
            items = stateTaskList.value,
            key = { it.id.hashCode() }
        ) { taskDTO ->
            TaskItem(
                modifier = modifier,
                taskDTO = taskDTO,
                deleteTaskDTO = { deleteTaskDTO(it) },
                changeFavoriteTaskDTO = { changeFavoriteTaskDTO(it) },
                restoreTaskDelete = {},
                getItemTaskDTO = { getItemTaskDTO(it) }
            )
        }
    }
}