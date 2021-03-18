package com.example.AppTodoList.data.remote

import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.entities.TaskResponse
import com.example.AppTodoList.data.entities.TasksList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TasksService {
    @GET("tasks")
    suspend fun getAllTasks() : Response<TasksList>

    @GET("tasks/{id}")
    suspend fun getTask(@Path("id") id: Int): Response<Task>

    @POST("tasks/")
    suspend fun setTask(@Body task:Task): Response<TaskResponse>
}