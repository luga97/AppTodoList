package com.example.AppTodoList.data.entities

data class TaskResponse(
        val error: String,
        val status: String,
        val body: Task
)
