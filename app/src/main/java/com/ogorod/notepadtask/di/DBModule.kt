package com.ogorod.notepadtask.di

import android.content.Context
import androidx.room.Room
import com.ogorod.notepadtask.data.storage.room.task.TaskDAO
import com.ogorod.notepadtask.data.storage.room.task.TaskDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    //    @Provides
//    @Singleton
//    fun provideRoomDB(@ApplicationContext context: Context): TaskDB = TaskDB.getDB(context)
    /** know to the how import room */
    @Provides
    @Singleton
    fun provideRoomTaskDB(@ApplicationContext context: Context): TaskDB = Room.databaseBuilder(
        context = context, TaskDB::class.java,
        "task_database"
    ).build()

    @Provides
    @Singleton
    fun provideTaskDAO(taskDB: TaskDB): TaskDAO = taskDB.taskDAO

}