package com.example.AppTodoList.ui.createtask

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.repository.TasksRepository

class CreateTaskViewModel @ViewModelInject constructor(
        private val repository: TasksRepository
) : ViewModel()  {
    fun setTask(task: Task) = repository.setTask(task)
}