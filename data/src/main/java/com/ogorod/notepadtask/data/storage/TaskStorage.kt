package com.ogorod.notepadtask.data.storage

import com.ogorod.notepadtask.domain.dto.TaskDTO
import kotlinx.coroutines.flow.Flow

interface TaskStorage {
    suspend fun save(taskDTO: TaskDTO)
    suspend fun update(taskDTO: TaskDTO)
    suspend fun cancelDeleteTask(taskDTO: TaskDTO)
    suspend fun restoreAllDeleteTasks(vararg tasks: TaskDTO)

    fun search(searchTask: String): Flow<List<TaskDTO>>
    fun getAll(): Flow<List<TaskDTO>>
    fun getAllFavorite(): Flow<List<TaskDTO>>
    fun getAllTimerDeleteTasks(): Flow<List<TaskDTO>>

    suspend fun setTimerDeleteTasks(vararg taskDTO: TaskDTO)
    suspend fun delete(vararg taskDTO: TaskDTO)


}