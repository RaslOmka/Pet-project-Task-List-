package com.ogorod.notepadtask.di

import com.ogorod.notepadtask.domain.repository.TaskRepository
import com.ogorod.notepadtask.domain.services.TaskService
import com.ogorod.notepadtask.domain.services.TaskServiceImpl
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
/**
 *  check why viewModel is not selected, and why useCase is not singleton selected
 * првоерить почему стоит viewModel,  и почему юскейсы  не сингелтон
 */
object DomainModule {
    @Provides
    fun provideTaskService(
        taskRepository: TaskRepository
    ): TaskService = TaskServiceImpl(
        getAllTaskUseCase = GetAllTaskUseCase(taskRepository = taskRepository),
        saveTaskUseCase = SaveTaskUseCase(taskRepository = taskRepository),
        deleteUseCase = DeleteTaskUseCase(taskRepository = taskRepository),
        searchUseCase = SearchTaskUseCase(taskRepository = taskRepository),
        changeFavoriteTaskUseCase = ChangeFavoriteTaskUseCase(taskRepository = taskRepository),
        setTimerDeleteUseCase = SetTimerDeleteUseCase(taskRepository = taskRepository),
        cancelDeleteTaskUseCase = CancelDeleteTaskUseCase(taskRepository = taskRepository),
        restoreAllDeleteTaskUseCase = RestoreAllDeleteTaskUseCase(taskRepository = taskRepository),
        getFavoriteUseCase = GetFavoriteUseCase(taskRepository = taskRepository),
        getAllTimerDeleteUseCase = GetAllTimerDeleteUseCase(taskRepository = taskRepository)
    )


    @Provides
    fun provideDeleteTaskUseCase(taskRepository: TaskRepository): DeleteTaskUseCase =
        DeleteTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideGetFavoriteUseCase(taskRepository: TaskRepository): GetFavoriteUseCase =
        GetFavoriteUseCase(taskRepository = taskRepository)

    @Provides
    fun provideGetTaskUseCase(taskRepository: TaskRepository): GetAllTaskUseCase =
        GetAllTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideSaveTaskUseCase(taskRepository: TaskRepository): SaveTaskUseCase =
        SaveTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideSearchTaskUseCase(taskRepository: TaskRepository): SearchTaskUseCase =
        SearchTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideChangeTaskUseCase(taskRepository: TaskRepository): ChangeFavoriteTaskUseCase =
        ChangeFavoriteTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideSetTimerDeleteTaskUseCase(taskRepository: TaskRepository): SetTimerDeleteUseCase =
        SetTimerDeleteUseCase(taskRepository = taskRepository)

    @Provides
    fun provideCancelDeleteTaskUseCase(taskRepository: TaskRepository): CancelDeleteTaskUseCase =
        CancelDeleteTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideCancelAllDeleteTaskUseCase(taskRepository: TaskRepository): RestoreAllDeleteTaskUseCase =
        RestoreAllDeleteTaskUseCase(taskRepository = taskRepository)

    @Provides
    fun provideGetAllTimerDeleteTaskUseCase(taskRepository: TaskRepository): GetAllTimerDeleteUseCase =
        GetAllTimerDeleteUseCase(taskRepository = taskRepository)

}
