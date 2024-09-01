package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository

class ChangeFavoriteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(taskDTO: TaskDTO) {
        val copyTask = taskDTO.copy(favorite = !taskDTO.favorite)
        taskRepository.updateTask(taskDTO = copyTask)
    }
}