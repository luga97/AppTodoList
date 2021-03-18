package com.example.AppTodoList.data.repository

import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.local.TasksDao
import com.example.AppTodoList.data.remote.TasksRemoteDataSource
import com.example.AppTodoList.utils.performGetOperation
import com.example.AppTodoList.utils.performPostOperation
import javax.inject.Inject

/**
 * Repositorio que nos permite abstraer de el patron MVVVM
 * la logica de acceso a las fuentes de datos, y asi
 * nuestro sistema este menos acoplado
 * @param remoteDataSource fuente de datos externa (API)
 * @param localDataSource fuente de datos local (Room database)
 * @author Luis Garcia
 */
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