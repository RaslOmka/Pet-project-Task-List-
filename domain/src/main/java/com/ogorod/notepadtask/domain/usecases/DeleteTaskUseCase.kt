package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository

// сделать удаление только тасков с таймером
class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(vararg tasks: TaskDTO) {
        taskRepository.deleteTasks(taskDTO = tasks)
    }
}