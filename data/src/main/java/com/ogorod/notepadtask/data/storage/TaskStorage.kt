package com.ogorod.notepadtask.data.storage

import com.ogorod.notepadtask.data.model.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskStorage {
    suspend fun save(task: Task)
    suspend fun update(task: Task)
    suspend fun cancelDeleteTask(task: Task)
    suspend fun restoreAllDeleteTasks(vararg tasks: Task)

    fun search(searchTask: String): Flow<List<Task>>
    fun getAll(): Flow<List<Task>>
    fun getAllFavorite(): Flow<List<Task>>
    fun getAllTimerDeleteTasks(): Flow<List<Task>>

    suspend fun setTimerDeleteTasks(vararg task: Task)
    suspend fun delete(vararg task: Task)
}