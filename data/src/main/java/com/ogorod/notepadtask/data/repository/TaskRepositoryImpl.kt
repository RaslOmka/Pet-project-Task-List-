package com.ogorod.notepadtask.data.repository

import com.ogorod.notepadtask.data.storage.TaskStorage
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(private val taskStorage: TaskStorage) : TaskRepository {

    override suspend fun saveTask(taskDTO: TaskDTO) {
        taskStorage.save(taskDTO = taskDTO)
    }

    override fun searchTask(searchTaskDTO: String): Flow<List<TaskDTO>> {
        return taskStorage.search(searchTask = searchTaskDTO)
    }

    override fun getAllTask(): Flow<List<TaskDTO>> {
        return taskStorage.getAll()
    }

    override suspend fun deleteTasks(vararg taskDTO: TaskDTO) {
        taskStorage.delete(taskDTO = taskDTO)
    }

    override suspend fun restoreAllDeleteTasks(vararg taskDTO: TaskDTO) {
        taskStorage.restoreAllDeleteTasks(tasks = taskDTO)
    }

    override suspend fun setTimerDeleteTasks(vararg taskDTO: TaskDTO) {
        taskStorage.setTimerDeleteTasks(taskDTO = taskDTO)
    }

    override fun getAllTimerDeleteTasks(): Flow<List<TaskDTO>> =
        taskStorage.getAllTimerDeleteTasks()


    override suspend fun cancelDeleteTask(taskDTO: TaskDTO) {
        taskStorage.cancelDeleteTask(taskDTO = taskDTO)
    }

    override suspend fun updateTask(taskDTO: TaskDTO) {
        taskStorage.update(taskDTO = taskDTO)
    }

    override fun getFavorite(): Flow<List<TaskDTO>> {
        return taskStorage.getAllFavorite()
    }


}