package com.example.AppTodoList.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.AppTodoList.data.repository.TasksRepository

class TasksViewModel @ViewModelInject constructor(
    private val repository: TasksRepository
) : ViewModel() {
    val characters = repository.getTasks()
}
