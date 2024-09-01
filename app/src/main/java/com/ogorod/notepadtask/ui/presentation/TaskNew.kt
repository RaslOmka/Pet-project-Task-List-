package com.ogorod.notepadtask.ui.presentation

import android.icu.text.DateFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.ogorod.notepadtask.domain.dto.TaskDTO
import java.util.Date

@Composable
fun NewTask(
    modifier: Modifier = Modifier,
    customModifier: Modifier,
    saveTask: (newTask: TaskDTO) -> Unit,
    navigateBack: () -> Unit
) {
    val dataText = rememberSaveable {
        mutableStateOf(DateFormat.getDateTimeInstance().format(Date()))
    }
    val title = rememberSaveable {
        mutableStateOf("")
    }
    val text = rememberSaveable {
        mutableStateOf("")
    }

    TaskScreen(
        modifier = modifier,
        customModifier = customModifier,
        title = title.value,
        text = text.value,
        date = dataText.value,
        readOnly = false,
        changeTitle = { title.value = it },
        changeText = { text.value = it },
        navigateBack = navigateBack,
        saveTask = {
            val task = TaskDTO(
                id = null,
                title = title.value,
                text = text.value,
                favorite = false,
                dateCreate = dataText.value
            )
            saveTask(task)
        }
    )
}