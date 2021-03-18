package com.example.AppTodoList.ui.edittask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.repository.TasksRepository
import com.example.AppTodoList.utils.Resource

/**
 * VieModel Exclusivo para EditTaskFragment
 * nos permite precargar los datos de la tarea
 * en los campos de texto antes de editarla y
 * asi proveer un UX un poco mas agradable para el usuario
 *
 * Tambien nos permite enviar la modificacion a la capa de datos
 *@param repository repositorio que nos permita acceder a la capa de datos
 * @author Luis Garcia
 */
class EditTaskViewModel @ViewModelInject constructor(
    private val repository: TasksRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _item = _id.switchMap { id ->
        repository.getTask(id)
    }
    val task: LiveData<Task> = _item

    fun setTask(task: Task) = repository.setTask(task)

    fun start(id: String) {
        _id.value = id
    }

}
