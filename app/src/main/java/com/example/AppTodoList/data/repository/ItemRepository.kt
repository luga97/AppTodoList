package com.example.AppTodoList.data.repository

import com.example.AppTodoList.data.local.ItemDao
import com.example.AppTodoList.data.remote.itemRemoteDataSource
import com.example.AppTodoList.utils.performGetOperation
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val remoteDataSource: itemRemoteDataSource,
    private val localDataSource: ItemDao
) {

    fun getItem(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getItems() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}