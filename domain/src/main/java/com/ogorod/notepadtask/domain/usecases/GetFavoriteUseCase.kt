package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class GetFavoriteUseCase(private val taskRepository: TaskRepository) {
         fun execute(): Flow<List<TaskDTO>> {
        return taskRepository.getFavorite()
    }
}