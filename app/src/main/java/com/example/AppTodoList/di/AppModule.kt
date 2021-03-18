package com.example.AppTodoList.di

import android.content.Context
import com.example.AppTodoList.data.local.AppDatabase
import com.example.AppTodoList.data.local.TasksDao
import com.example.AppTodoList.data.remote.TasksRemoteDataSource
import com.example.AppTodoList.data.remote.TasksService
import com.example.AppTodoList.data.repository.TasksRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://app-todo-list-2021.herokuapp.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideTasksService(retrofit: Retrofit): TasksService = retrofit.create(TasksService::class.java)

    @Singleton
    @Provides
    fun provideItemRemoteDataSource(tasksService: TasksService) = TasksRemoteDataSource(tasksService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideTasksmDao(db: AppDatabase) = db.tasksDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: TasksRemoteDataSource,
                          localDataSource: TasksDao) =
        TasksRepository(remoteDataSource, localDataSource)
}