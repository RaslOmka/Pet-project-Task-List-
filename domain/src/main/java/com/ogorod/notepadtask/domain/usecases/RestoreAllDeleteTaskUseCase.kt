package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository

class RestoreAllDeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(vararg taskDTOS: TaskDTO) {
        val tempTasks = taskDTOS.map { list -> list.copy(delete = false, timerDelete = 0) }
        taskRepository.restoreAllDeleteTasks(taskDTO = tempTasks.toTypedArray())
    }
}