package com.example.AppTodoList.ui.itemdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.AppTodoList.data.entities.Item
import com.example.AppTodoList.data.repository.ItemRepository
import com.example.AppTodoList.utils.Resource

class ItemDetailViewModel @ViewModelInject constructor(
    private val repository: ItemRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getItem(id)
    }
    val item: LiveData<Resource<Item>> = _character


    fun start(id: Int) {
        _id.value = id
    }

}
