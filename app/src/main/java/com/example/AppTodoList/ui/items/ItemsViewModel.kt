package com.example.AppTodoList.ui.items

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.AppTodoList.data.repository.ItemRepository

class ItemsViewModel @ViewModelInject constructor(
    private val repository: ItemRepository
) : ViewModel() {
    val characters = repository.getItems()
}
