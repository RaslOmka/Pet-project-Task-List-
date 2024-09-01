package com.ogorod.notepadtask.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.ogorod.notepadtask.domain.dto.TaskDTO

@Composable
fun TaskDataUpdate(
    modifier: Modifier = Modifier,
    customModifier: Modifier,
    taskDTO: TaskDTO,
    updateTask: (taskDTO: TaskDTO) -> Unit,
    restoreTask: (taskDTO: TaskDTO) -> Unit,
    navigateBack: () -> Unit
) {

    val title = rememberSaveable {
        mutableStateOf(taskDTO.title)
    }
    val text = rememberSaveable {
        mutableStateOf(taskDTO.text)
    }
    TaskScreen(
        modifier = modifier,
        customModifier = customModifier,
        title = title.value,
        text = text.value,
        date = taskDTO.dateCreate,
        readOnly = taskDTO.delete,
        changeTitle = { title.value = it },
        changeText = { text.value = it },
        navigateBack = navigateBack

    ) {
        if (taskDTO.delete) {
            restoreTask(taskDTO)
        } else {
            updateTask(taskDTO.copy(title = title.value, text = text.value))

        }
    }

}