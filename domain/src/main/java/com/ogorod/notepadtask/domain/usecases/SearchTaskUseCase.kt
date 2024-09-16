package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class SearchTaskUseCase(private val taskRepository: TaskRepository) {

    fun execute(searchTask: String): Flow<List<TaskDTO>> =
        taskRepository.searchTask(searchTaskDTO = "%$searchTask%")

}