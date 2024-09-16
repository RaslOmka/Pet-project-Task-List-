package com.ogorod.notepadtask.data.repository

import com.ogorod.notepadtask.data.mapper.TaskEntityMapper
import com.ogorod.notepadtask.data.storage.TaskStorage
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskStorage: TaskStorage,
    private val taskMapper: TaskEntityMapper
) : TaskRepository {

    override suspend fun saveTask(taskDTO: TaskDTO) {
        val task = taskMapper.fromDTO(taskDTO = taskDTO)
        taskStorage.save(task = task)
    }

    override fun searchTask(searchTaskDTO: String): Flow<List<TaskDTO>> {
        val tasks = taskStorage.search(searchTask = searchTaskDTO)
        return tasks.map { list -> list.map { task -> taskMapper.toDTO(task) } }
    }


    override fun getAllTask(): Flow<List<TaskDTO>> =
        taskStorage.getAll().map { list -> list.map { task -> taskMapper.toDTO(task) } }


    override suspend fun deleteTasks(vararg taskDTO: TaskDTO) {
        val deleteListTask = taskDTO.map { task -> taskMapper.fromDTO(task) }
        taskStorage.delete(task = deleteListTask.toTypedArray())
    }

    override fun getAllTimerDeleteTasks(): Flow<List<TaskDTO>> =
        taskStorage.getAllTimerDeleteTasks()
            .map { list -> list.map { task -> taskMapper.toDTO(task) } }

    override suspend fun setTimerDeleteTasks(vararg taskDTO: TaskDTO) {
        val deleteTimerListTask = taskDTO.map { task -> taskMapper.fromDTO(task) }
        taskStorage.setTimerDeleteTasks(task = deleteTimerListTask.toTypedArray())
    }

    override suspend fun cancelDeleteTask(taskDTO: TaskDTO) {
        taskStorage.cancelDeleteTask(task = taskMapper.fromDTO(taskDTO))
    }

    override suspend fun restoreAllDeleteTasks(vararg taskDTO: TaskDTO) {
        val restoreListTask = taskDTO.map { task -> taskMapper.fromDTO(task) }
        taskStorage.restoreAllDeleteTasks(tasks = restoreListTask.toTypedArray())
    }

    override suspend fun updateTask(taskDTO: TaskDTO) {
        taskStorage.update(task = taskMapper.fromDTO(taskDTO))
    }

    override fun getFavorite(): Flow<List<TaskDTO>> =
        taskStorage.getAllFavorite().map { list -> list.map { task -> taskMapper.toDTO(task) } }

}