package com.ogorod.notepadtask.ui.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.ui_components.Fab
import com.ogorod.notepadtask.ui.ui_components.MainSearchBar
import com.ogorod.notepadtask.ui.ui_components.MainSnackBar
import com.ogorod.notepadtask.ui.ui_components.MainTopBar
import com.ogorod.notepadtask.ui.ui_components.TasksLazyColumn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    customModifier: Modifier,
    stateTaskList: State<List<TaskDTO>>,
    isFavorite: State<Boolean>,
    searchTask: (input: String) -> Unit,
    setTimerDeleteTaskDTO: (taskDTO: TaskDTO) -> Unit,
    changeFavoriteTask: (taskDTO: TaskDTO) -> Unit,
    getItemTaskDTO: (taskDTO: TaskDTO) -> Unit,
    cancelDelete: (TaskDTO) -> Unit,
    getFavoriteTasks: () -> Unit,
    navigateToNewTaskScreen: () -> Unit,
    navigateToTaskUpdateScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit,
    checkIsFavorite: () -> Unit
) {

    val behavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val searchBarState = rememberSaveable { mutableStateOf(false) }

    val messageToast = stringResource(id = R.string.message_toast)
    val snackBarHostState = remember { SnackbarHostState() }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(behavior.nestedScrollConnection),
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { MainSnackBar(it) }
            )
        },
        topBar = {
            if (searchBarState.value)
                MainSearchBar(
                    modifier = modifier,
                    customModifier = customModifier,
                    taskList = stateTaskList,
                    openSearchBar = { searchBarState.value = !searchBarState.value },
                    taskInputSearch = { searchTask(it) },
                    deleteTaskOnSearchBar = { taskDTO ->
                        setTimerDeleteTaskDTO(taskDTO)
                        showSnackBar(
                            coroutineScope = coroutineScope,
                            snackBarHostState = snackBarHostState,
                            context = context,
                            messageToast = messageToast,
                            cancelDelete = { cancelDelete(taskDTO) }
                        )
                    },
                    favoriteChangeTask = { changeFavoriteTask(it) },
                    checkIsFavorite = { checkIsFavorite() },
                    getItemTaskDTO = {
                        getItemTaskDTO(it)
                        navigateToTaskUpdateScreen()
                    }
                )
            else
                MainTopBar(
                    behavior = behavior,
                    openSearchBar = { searchBarState.value = !searchBarState.value },
                    getAllFavorites = { getFavoriteTasks() },
                    openSettingsScreen = { navigateToSettingsScreen() },
                    isFavorite = isFavorite
                )
        },
        floatingActionButton = {
            Fab(createNewTask = { navigateToNewTaskScreen() })
        },
        containerColor = NotepadTaskTheme.customColor.extraBackground
    ) { paddingContent ->

        TasksLazyColumn(
            modifier = modifier,
            customModifier = customModifier,
            paddingValues = paddingContent,
            stateTaskList = stateTaskList,
            deleteTaskDTO = { taskDTO ->
                setTimerDeleteTaskDTO(taskDTO)
                showSnackBar(
                    coroutineScope = coroutineScope,
                    snackBarHostState = snackBarHostState,
                    context = context,
                    messageToast = messageToast,
                    cancelDelete = {
                        cancelDelete(taskDTO)
                    }
                )
            },
            changeFavoriteTaskDTO = { changeFavoriteTask(it) },
            getItemTaskDTO = {
                getItemTaskDTO(it)
                navigateToTaskUpdateScreen()
            }
        )
    }
}

private fun showSnackBar(
    coroutineScope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    context: Context,
    messageToast: String,
    cancelDelete: () -> Unit
) {
    coroutineScope.launch {
        val result =
            snackBarHostState.showSnackbar(
                message = "",
                duration = SnackbarDuration.Indefinite
            )

        when (result) {
            SnackbarResult.Dismissed -> {
                cancelDelete()
            }

            SnackbarResult.ActionPerformed -> {
                Toast.makeText(context, messageToast, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
