package com.example.AppTodoList.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.AppTodoList.data.entities.Task

/**
 * Base de datos creada con el fin de almacenar en cache las tareas
 * en caso de que el usuario no posea acceso a internet
 * @property tasksDao funciona como interfaz para realizar consultas
 * y modificaciones a la tabla de tareas
 * @author Luis Garcia
 */
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "tasks")
                .fallbackToDestructiveMigration()
                .build()
    }

}