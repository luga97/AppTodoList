package com.example.AppTodoList.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Crea una tabla para representar las tareas en sqlite y
 * tambien permite crear un DTO para enviar y recibir tareas desde la API
 * @author Luis Garcia
 */
@Entity(tableName = "items")
data class Task(
    var title: String,
    var description: String
) {
    constructor(id: String,title: String,description: String) : this(title,description) {
        this.id = id
    }

    @PrimaryKey
    var id: String = ""
}