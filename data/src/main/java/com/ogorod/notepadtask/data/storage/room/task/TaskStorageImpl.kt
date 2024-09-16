package com.ogorod.notepadtask.data.storage.room.task

import com.ogorod.notepadtask.data.model.entities.Task
import com.ogorod.notepadtask.data.storage.TaskStorage
import kotlinx.coroutines.flow.Flow

class TaskStorageImpl(private val taskDAO: TaskDAO) : TaskStorage {
    //сохранить атску
    override suspend fun save(task: Task) {
        taskDAO.insert(task = task)
    }

    //найти таски по совпадению слов
    override fun search(searchTask: String): Flow<List<Task>> =
        taskDAO.searchTextLike(searchText = searchTask)

    //вернуть все таски кроме почмеченых на удаление
    override fun getAll(): Flow<List<Task>> =
        taskDAO.getAll()

    //удалить  таску(и)
    override suspend fun delete(vararg task: Task) {
        taskDAO.delete(tasks = task)
    }

    //вернуть все таски почмеченых на удаление
    override fun getAllTimerDeleteTasks(): Flow<List<Task>> =
        taskDAO.loadAllTDeleteTask()


    //отметить таску(и) на удаление
    override suspend fun setTimerDeleteTasks(vararg task: Task) {
        taskDAO.setTimerDelete(tasks = task)
    }

    //отменить удаление таски
    override suspend fun cancelDeleteTask(task: Task) {
        taskDAO.cancelDeleteTask(task)

    }

    //востановить все таски их корзины
    override suspend fun restoreAllDeleteTasks(vararg tasks: Task) {
        taskDAO.restoreAllDeleteTasks(tasks = tasks)
    }


    //обновить таску
    override suspend fun update(task: Task) {
        taskDAO.update(task)

    }

    //вернуть все таки отмеченные как избранные
    override fun getAllFavorite(): Flow<List<Task>> =
        taskDAO.loadAllTaskFavorite()

}
