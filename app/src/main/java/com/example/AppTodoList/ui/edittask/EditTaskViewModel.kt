package com.example.AppTodoList.ui.edittask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.repository.TasksRepository
import com.example.AppTodoList.utils.Resource

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
