package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository

class SaveTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(task: TaskDTO) {
        taskRepository.saveTask(taskDTO = task)
    }
}