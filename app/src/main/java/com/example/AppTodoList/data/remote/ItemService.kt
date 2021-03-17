package com.example.AppTodoList.data.remote

import com.example.AppTodoList.data.entities.Item
import com.example.AppTodoList.data.entities.ItemList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemService {
    @GET("character")
    suspend fun getAllItems() : Response<ItemList>

    @GET("character/{id}")
    suspend fun getItem(@Path("id") id: Int): Response<Item>
}