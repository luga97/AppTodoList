package com.example.AppTodoList.data.entities

data class TasksList(
    val error: String,
    val status: String,
    val body: List<Task>
)