package com.ogorod.notepadtask.ui.navigation

sealed class Routes( val rout: String) {
    object Home: Routes(rout = "home")
    object NewTask: Routes(rout = "newTask")
    object UpdateTask: Routes(rout = "updateTask")
    object Settings: Routes(rout = "settings")
    object SettingsDialog: Routes(rout = "dialog")
    object TaskRemoteScreen: Routes(rout = "taskRemoteScreen")
    object CustomDialog: Routes(rout = "customDialog")
}