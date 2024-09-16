package com.ogorod.notepadtask.di

import com.ogorod.notepadtask.data.mapper.TaskEntityMapper
import com.ogorod.notepadtask.data.repository.TaskRepositoryImpl
import com.ogorod.notepadtask.data.storage.TaskStorage
import com.ogorod.notepadtask.data.storage.room.task.TaskDAO
import com.ogorod.notepadtask.data.storage.room.task.TaskStorageImpl
import com.ogorod.notepadtask.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskStorage: TaskStorage,
        taskMapper: TaskEntityMapper
    ): TaskRepository =
        TaskRepositoryImpl(taskStorage = taskStorage, taskMapper = taskMapper)


    @Provides
    @Singleton
    fun provideTaskStorage(taskDAO: TaskDAO): TaskStorage =
        TaskStorageImpl(taskDAO = taskDAO)

    @Provides
    @Singleton
    fun provideTaskEntityMapper(taskMapper: TaskEntityMapper): TaskEntityMapper =
        TaskEntityMapper()
}