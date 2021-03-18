package com.example.AppTodoList.data.entities

/**
 * DTO para recibir la solicitud de la API
 * cuando se crea o modifica una tarea
 * @author Luis Garcia
 */
data class TaskResponse(
        val error: String,
        val status: String,
        val body: Task
)
