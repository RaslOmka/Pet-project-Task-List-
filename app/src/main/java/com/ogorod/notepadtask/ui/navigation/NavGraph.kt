package com.ogorod.notepadtask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.ogorod.notepadtask.ui.presentation.MainScreen
import com.ogorod.notepadtask.ui.presentation.NewTask
import com.ogorod.notepadtask.ui.presentation.SettingsDialogTheme
import com.ogorod.notepadtask.ui.presentation.SettingsScreen
import com.ogorod.notepadtask.ui.presentation.TaskDataUpdate
import com.ogorod.notepadtask.ui.presentation.TaskRemoteScreen
import com.ogorod.notepadtask.ui.theme.CustomCorner
import com.ogorod.notepadtask.ui.theme.CustomPallet
import com.ogorod.notepadtask.ui.theme.CustomSize
import com.ogorod.notepadtask.ui.theme.ThemeModeSelection
import com.ogorod.notepadtask.ui.viewmodels.MainScreenViewModel

@Composable
fun StartApp(
    modifier: Modifier = Modifier,
    customModifier: Modifier,
    darkTheme: Boolean,
    navController: NavHostController,
    textSizeIndex: Int,
    shapeCornerIndex: Int,
    customPalletIndex: Int,
    themeModeIndex: Int,
    setSizeText: (CustomSize) -> Unit,
    setRoundingCorner: (CustomCorner) -> Unit,
    setCustomPallet: (CustomPallet) -> Unit,
    setThemeMode: (ThemeModeSelection) -> Unit,
    mainScreenViewModel: MainScreenViewModel
) {

    NavHost(navController = navController, startDestination = Routes.Home.rout) {

        composable(Routes.Home.rout) {
            MainScreen(
                modifier = modifier,
                customModifier = customModifier,
                isFavorite = mainScreenViewModel.isFavorite,
                stateTaskList = mainScreenViewModel.taskList,
                navigateToNewTaskScreen = { navController.navigate(Routes.NewTask.rout) },
                navigateToTaskUpdateScreen = { navController.navigate(Routes.UpdateTask.rout) },
                navigateToSettingsScreen = { navController.navigate(Routes.Settings.rout) },
                searchTask = { mainScreenViewModel.searchTask(it) },
                setTimerDeleteTaskDTO = { mainScreenViewModel.setTimerDeleteTask(it) },
                changeFavoriteTask = { mainScreenViewModel.updateTask(it) },
                getItemTaskDTO = { mainScreenViewModel.taskTemp = it },
                getFavoriteTasks = { mainScreenViewModel.getFavoriteTasks() },
                cancelDelete = { mainScreenViewModel.cancelDeleteTask(it) },
                checkIsFavorite = { mainScreenViewModel.checkIsFavorite() }
            )
        }

        composable(Routes.NewTask.rout) {
            NewTask(modifier = modifier,
                customModifier = customModifier,
                saveTask = { mainScreenViewModel.addTask(it) },
                navigateBack = { navController.navigateUp() })
        }

        composable(Routes.UpdateTask.rout) {
            TaskDataUpdate(modifier = modifier,
                customModifier = customModifier,
                taskDTO = mainScreenViewModel.taskTemp!!,
                updateTask = { mainScreenViewModel.updateTask(it) },
                restoreTask = {
                    mainScreenViewModel.cancelDeleteTask(it)
                    navController.navigate(Routes.TaskRemoteScreen.rout)
                },
                navigateBack = { navController.navigateUp() })
        }
        composable(Routes.Settings.rout) {
            SettingsScreen(
                modifier = modifier,
                darkTheme = darkTheme,
                themeModeIndex = themeModeIndex,
                textSizeIndex = textSizeIndex,
                shapeCornerIndex = shapeCornerIndex,
                customPalletIndex = customPalletIndex,
                setSizeText = { setSizeText(it) },
                setRoundingCorner = { setRoundingCorner(it) },
                setCustomPallet = { setCustomPallet(it) },
                navigateBack = { navController.navigateUp() },
                navigateToDialogScreenThemeMode = { navController.navigate(Routes.SettingsDialog.rout) },
                navigateToTaskRemoteScreen = {
                    mainScreenViewModel.getAllTimerDeleteTasks()
                    navController.navigate(Routes.TaskRemoteScreen.rout)
                }
            )
        }

        dialog(Routes.SettingsDialog.rout) {
            SettingsDialogTheme(
                modifier = modifier,
                themeModeIndex = themeModeIndex,
                setThemMode = { setThemeMode(it) },
                navigateBack = { navController.navigateUp() })
        }
        dialog(Routes.TaskRemoteScreen.rout) {
            TaskRemoteScreen(
                modifier = modifier,
                listTaskDTO = mainScreenViewModel.taskListTimerDelete,
                deleteTask = { mainScreenViewModel.deleteTask(it) },
                deleteAllTask = { mainScreenViewModel.deleteTask(task = it.toTypedArray()) },
                restoreAllTasks = { mainScreenViewModel.restoreAllDeleteTasks(tasksDTO = it.toTypedArray()) },
                restoreTask = { mainScreenViewModel.cancelDeleteTask(it) },
                getItemTaskDTO = {
                    mainScreenViewModel.taskTemp = it
                    navController.navigate(Routes.UpdateTask.rout)
                },
                navigateBack = { navController.navigateUp() },
                cancelJobListDelete = { mainScreenViewModel.cancelJobListTaskDelete() },
            )
        }
    }
}
