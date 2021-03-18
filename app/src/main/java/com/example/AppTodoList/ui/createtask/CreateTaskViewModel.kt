package com.example.AppTodoList.ui.createtask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.repository.TasksRepository

/**
 * ViewModel Exclusivo para CreateTaskFragment, nos permite crear una nueva tarea
 * @param repository repositorio que nos permita acceder a la capa de datos
 * @author Luis Garcia
 */
class CreateTaskViewModel @ViewModelInject constructor(
        private val repository: TasksRepository
) : ViewModel()  {
    fun createTask(task: Task) = repository.setTask(task)
}