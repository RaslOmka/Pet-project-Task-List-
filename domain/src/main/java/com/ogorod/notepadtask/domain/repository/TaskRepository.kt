package com.ogorod.notepadtask.domain.repository

import com.ogorod.notepadtask.domain.dto.TaskDTO
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun saveTask(taskDTO: TaskDTO)
    suspend fun updateTask(taskDTO: TaskDTO)
    fun getFavorite(): Flow<List<TaskDTO>>
    fun searchTask(searchTaskDTO: String): Flow<List<TaskDTO>>
    fun getAllTask(): Flow<List<TaskDTO>>
    suspend fun deleteTasks(vararg taskDTO: TaskDTO)
    suspend fun setTimerDeleteTasks(vararg taskDTO: TaskDTO)
    fun getAllTimerDeleteTasks(): Flow<List<TaskDTO>>
    suspend fun cancelDeleteTask(taskDTO: TaskDTO)
    suspend fun restoreAllDeleteTasks(vararg taskDTO: TaskDTO)
}