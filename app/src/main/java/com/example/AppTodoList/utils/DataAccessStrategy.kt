package com.example.AppTodoList.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.AppTodoList.data.entities.Task
import com.example.AppTodoList.data.entities.TaskResponse
import com.example.AppTodoList.utils.Resource.Status.*
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }

        val response = networkCall.invoke()
        if (response.status == SUCCESS) {
            saveCallResult(response.data!!)
            emitSource(source)

        } else if (response.status == ERROR) {
            emit(Resource.error(response.message!!))
            emitSource(source)
        }
    }

fun performPostOperation(databaseQuery: suspend (Task) -> Unit,
                             networkCall: suspend () -> Resource<TaskResponse>): LiveData<Resource<Task>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = networkCall.invoke()
        if (response.status == SUCCESS) {
            val data = response.data!!
            databaseQuery(data.body)
            emit(Resource.success(data.body))
        } else if (response.status == ERROR) {
            emit(Resource.error(response.message!!))
        }
    }

