package com.ogorod.notepadtask.domain.usecases

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository

class SetTimerDeleteUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(vararg tasks: TaskDTO) {
        val tempTasks = tasks.map { list -> list.copy(delete = true, timerDelete = 30 * 86400) }
        taskRepository.setTimerDeleteTasks(taskDTO = tempTasks.toTypedArray())
    }
}