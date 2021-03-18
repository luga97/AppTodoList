package com.example.AppTodoList.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.AppTodoList.data.repository.TasksRepository
/**
 * ViewModel Exclusivo del fragmento TaskFragment
 * nos permitira obtener todas las tareas desde las
 * fuentes de datos
 * @param repository repositorio que nos permita acceder a la capa de datos
 * @author Luis Garcia
 */
class TasksViewModel @ViewModelInject constructor(
    private val repository: TasksRepository
) : ViewModel() {
    val characters = repository.getTasks()
}
