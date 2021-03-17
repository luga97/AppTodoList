package com.example.AppTodoList.data.remote

import javax.inject.Inject

class itemRemoteDataSource @Inject constructor(
    private val itemService: ItemService
): BaseDataSource() {
    suspend fun getCharacters() = getResult { itemService.getAllItems() }
    suspend fun getCharacter(id: Int) = getResult { itemService.getItem(id) }
}