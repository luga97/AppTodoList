package com.example.AppTodoList.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.AppTodoList.data.entities.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM items")
    fun getAllCharacters() : LiveData<List<Task>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getCharacter(id: String): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<Task>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)


}