package com.example.AppTodoList.data.remote

import com.example.AppTodoList.data.entities.Task
import javax.inject.Inject

class TasksRemoteDataSource @Inject constructor(
    private val itemService: TasksService
): BaseDataSource() {
    suspend fun getItems() = getResult { itemService.getAllTasks() }
    suspend fun getItem(id: Int) = getResult { itemService.getTask(id) }
    suspend fun setTask(task: Task) = getResult { itemService.setTask(task) }
}