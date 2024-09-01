package com.ogorod.notepadtask.data.storage.room.task

import com.ogorod.notepadtask.data.model.entities.Task
import com.ogorod.notepadtask.data.storage.TaskStorage
import com.ogorod.notepadtask.domain.dto.TaskDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskStorageImpl(private val taskDAO: TaskDAO) : TaskStorage {
    //сохранить атску
    override suspend fun save(taskDTO: TaskDTO) {
        val task = Task.taskEmpty.fromDTO(taskDTO = taskDTO)
        taskDAO.insert(task = task)
    }

    //найти таски по совпадению слов
    override fun search(searchTask: String): Flow<List<TaskDTO>> =
        taskDAO.searchTextLike(searchText = searchTask)
            .map { list -> list.map { task -> task.toDTO() } }

    //вернуть все таски кроме почмеченых на удаление
    override fun getAll(): Flow<List<TaskDTO>> =
        taskDAO.getAll().map { list -> list.map { task -> task.toDTO() } }

    //удалить  таску(и)
    override suspend fun delete(vararg taskDTO: TaskDTO) {
        val listTasks = taskDTO.map { taskDTO -> Task.taskEmpty.fromDTO(taskDTO) }
        taskDAO.delete(tasks = listTasks.toTypedArray())
    }

    //отметить таску(и) на удаление
    override suspend fun setTimerDeleteTasks(vararg taskDTO: TaskDTO) {
        val listTasks = taskDTO.map { taskDTO -> Task.taskEmpty.fromDTO(taskDTO) }
        taskDAO.setTimerDelete(tasks = listTasks.toTypedArray())
    }

    //вернуть все таски почмеченых на удаление
    override fun getAllTimerDeleteTasks(): Flow<List<TaskDTO>> =
        taskDAO.loadAllTDeleteTask().map { list -> list.map { task -> task.toDTO() } }

    //отменить удаление таски
    override suspend fun cancelDeleteTask(taskDTO: TaskDTO) {
        val task = Task.taskEmpty.fromDTO(taskDTO = taskDTO)
        taskDAO.cancelDeleteTask(task)

    }

    //востановить все таски их корзины
    override suspend fun restoreAllDeleteTasks(vararg tasksDTO: TaskDTO) {
        val listTasks = tasksDTO.map { taskDTO -> Task.taskEmpty.fromDTO(taskDTO) }
        taskDAO.restoreAllDeleteTasks(tasks = listTasks.toTypedArray())
    }


    //обновить таску
    override suspend fun update(taskDTO: TaskDTO) {
        val task = Task.taskEmpty.fromDTO(taskDTO = taskDTO)
        taskDAO.update(task)

    }

    //вернуть все таки отмеченные как избранные
    override fun getAllFavorite(): Flow<List<TaskDTO>> =
        taskDAO.loadAllTaskFavorite().map { list -> list.map { task -> task.toDTO() } }


}
