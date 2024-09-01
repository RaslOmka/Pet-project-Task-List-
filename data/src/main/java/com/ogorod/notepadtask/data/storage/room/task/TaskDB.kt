package com.ogorod.notepadtask.data.storage.room.task

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ogorod.notepadtask.data.model.entities.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
//@TypeConverters(Converters::class)
abstract class TaskDB : RoomDatabase() {
    abstract val taskDAO: TaskDAO

//    companion object {
//        fun getDB(context: Context): TaskDB = Room.databaseBuilder(
//            context,
//            TaskDB::class.java,
//            "task_database"
//        ).build()
//    }
}