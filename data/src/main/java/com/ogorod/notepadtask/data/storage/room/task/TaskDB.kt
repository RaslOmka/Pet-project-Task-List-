package com.ogorod.notepadtask.data.storage.room.task

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ogorod.notepadtask.data.model.entities.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDB : RoomDatabase() {
    abstract val taskDAO: TaskDAO
}