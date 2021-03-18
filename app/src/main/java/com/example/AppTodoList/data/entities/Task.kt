package com.example.AppTodoList.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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