package com.example.AppTodoList.data.repository

import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.local.TasksDao
import com.example.AppTodoList.data.remote.TasksRemoteDataSource
import com.example.AppTodoList.utils.performGetOperation
import com.example.AppTodoList.utils.performPostOperation
import javax.inject.Inject

class TasksRepository @Inject constructor(
        private val remoteDataSource: TasksRemoteDataSource,
        private val localDataSource: TasksDao
) {

    fun getTask(id: String) = localDataSource.getCharacter(id)

    fun getTasks() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getItems() },
        saveCallResult = { localDataSource.insertAll(it.body) }
    )

    fun setTask(task: Task) = performPostOperation(
            databaseQuery = { localDataSource.insert(it) },
            networkCall = { remoteDataSource.setTask(task) }
    )
}