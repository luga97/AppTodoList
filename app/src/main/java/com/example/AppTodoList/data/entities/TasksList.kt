package com.example.AppTodoList.data.entities

/**
 * DTO para recibir todas las tareas desde la API
 * @author Luis Garcia
 */
data class TasksList(
    val error: String,
    val status: String,
    val body: List<Task>
)