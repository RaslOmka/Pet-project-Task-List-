package com.ogorod.notepadtask.ui.viewmodels

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.domain.services.TaskService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Stable
class MainScreenViewModel @Inject constructor(private val taskService: TaskService) :
    ViewModel() {
    private var firstStart = true
    private val _taskList = mutableStateOf(emptyList<TaskDTO>())
    val taskList: State<List<TaskDTO>> = _taskList

    private val _taskListTimerDelete = mutableStateOf(emptyList<TaskDTO>())
    val taskListTimerDelete: State<List<TaskDTO>> = _taskListTimerDelete
    private var jobTaskListTimerDelete: Job? = null

    private var job: Job? = null
    var isFavorite = mutableStateOf(false)
        private set

    var taskTemp: TaskDTO? = null

    fun startFillList() {
        if (firstStart) {
            getAllTask()
            firstStart = false
        }
    }

    fun checkIsFavorite() {
        if (isFavorite.value) {
            job?.cancel()
            job = viewModelScope.launch {
                taskService.getFavorite().collect { favoriteTaskListDTO ->
                    _taskList.value = favoriteTaskListDTO
                }
            }
        } else {
            getAllTask()
        }
    }

    private fun getAllTask() {
        if (job != null) job?.cancel()
        job = viewModelScope.launch {
            taskService.getAllTask().collect { listTaskDTO ->
                _taskList.value = listTaskDTO
            }
        }
    }

    fun getAllTimerDeleteTasks() {
        jobTaskListTimerDelete = viewModelScope.launch(Dispatchers.IO) {
            taskService.getAllTimerDeleteTasks().collect { listTaskDTO ->
                _taskListTimerDelete.value = listTaskDTO
            }
        }
    }

    fun cancelJobListTaskDelete() {
        if (jobTaskListTimerDelete != null) {
            jobTaskListTimerDelete?.cancel()
            jobTaskListTimerDelete = null
        }

    }

    private fun showTaskFavorite() {
        isFavorite.value = !isFavorite.value
    }

    fun getFavoriteTasks() {
        showTaskFavorite()
        if (isFavorite.value && job != null) {
            job?.cancel()
            job = viewModelScope.launch {
                taskService.getFavorite().collect { favoriteTaskListDTO ->
                    _taskList.value = favoriteTaskListDTO
                }
            }
        } else {
            job?.cancel()
            job = viewModelScope.launch {
                taskService.getAllTask().collect { getAllTask ->
                    _taskList.value = getAllTask
                }
            }
        }
    }

    fun deleteTask(vararg task: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.deleteTasks(taskDTO = task)
        }
    }

    fun setTimerDeleteTask(vararg task: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.setTimerDeleteTasks(taskDTO = task)
        }
    }

    fun addTask(task: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.saveTask(taskDTO = task)
        }
    }

    fun updateTask(task: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.updateTask(taskDTO = task)
        }
    }

    fun cancelDeleteTask(task: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.cancelDeleteTask(taskDTO = task)
        }
    }

    fun restoreAllDeleteTasks(vararg tasksDTO: TaskDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            taskService.restoreAllDeleteTasks(taskDTO = tasksDTO)
        }
    }

    fun searchTask(searchTaskDTO: String) {
        if (job != null) job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            taskService.searchTask(searchTaskDTO).collect { listTaskDTO ->
                _taskList.value = listTaskDTO
            }
        }
    }
}

