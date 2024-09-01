package com.ogorod.notepadtask.domain.services

import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.usecases.CancelDeleteTaskUseCase
import com.ogorod.notepadtask.domain.usecases.ChangeFavoriteTaskUseCase
import com.ogorod.notepadtask.domain.usecases.DeleteTaskUseCase
import com.ogorod.notepadtask.domain.usecases.GetAllTaskUseCase
import com.ogorod.notepadtask.domain.usecases.GetAllTimerDeleteUseCase
import com.ogorod.notepadtask.domain.usecases.GetFavoriteUseCase
import com.ogorod.notepadtask.domain.usecases.RestoreAllDeleteTaskUseCase
import com.ogorod.notepadtask.domain.usecases.SaveTaskUseCase
import com.ogorod.notepadtask.domain.usecases.SearchTaskUseCase
import com.ogorod.notepadtask.domain.usecases.SetTimerDeleteUseCase
import kotlinx.coroutines.flow.Flow


class TaskServiceImpl(
    private val getAllTaskUseCase: GetAllTaskUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteUseCase: DeleteTaskUseCase,
    private val searchUseCase: SearchTaskUseCase,
    private val changeFavoriteTaskUseCase: ChangeFavoriteTaskUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setTimerDeleteUseCase: SetTimerDeleteUseCase,
    private val cancelDeleteTaskUseCase: CancelDeleteTaskUseCase,
    private val restoreAllDeleteTaskUseCase: RestoreAllDeleteTaskUseCase,
    private val getAllTimerDeleteUseCase: GetAllTimerDeleteUseCase
) : TaskService {

    override suspend fun saveTask(taskDTO: TaskDTO) {
        saveTaskUseCase.execute(task = taskDTO)
    }

    override fun searchTask(searchTaskDTO: String): Flow<List<TaskDTO>> {
        return searchUseCase.execute(searchTask = searchTaskDTO)
    }

    override fun getAllTask(): Flow<List<TaskDTO>> {
        return getAllTaskUseCase.execute()
    }

    override suspend fun deleteTasks(vararg taskDTO: TaskDTO) {
        deleteUseCase.execute(tasks = taskDTO)
    }

    override suspend fun setTimerDeleteTasks(vararg taskDTO: TaskDTO) {
        setTimerDeleteUseCase.execute(tasks = taskDTO)
    }

    override fun getAllTimerDeleteTasks(): Flow<List<TaskDTO>> =
        getAllTimerDeleteUseCase.execute()


    override suspend fun cancelDeleteTask(taskDTO: TaskDTO) {
        cancelDeleteTaskUseCase.execute(taskDTO)
    }

    override suspend fun restoreAllDeleteTasks(vararg taskDTO: TaskDTO) {
        restoreAllDeleteTaskUseCase.execute(taskDTOS = taskDTO)
    }

    override suspend fun updateTask(taskDTO: TaskDTO) {
        changeFavoriteTaskUseCase.execute(taskDTO)
    }

    override fun getFavorite(): Flow<List<TaskDTO>> {
        return getFavoriteUseCase.execute()
    }


}