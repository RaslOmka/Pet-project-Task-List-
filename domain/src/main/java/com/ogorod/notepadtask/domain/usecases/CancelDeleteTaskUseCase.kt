package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository

class CancelDeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(taskDTO: TaskDTO) {
        val taskDTOCopy = taskDTO.copy(delete = false, timerDelete = null)
        taskRepository.cancelDeleteTask(taskDTOCopy)
    }
}