package com.example.AppTodoList.data.remote

import com.example.AppTodoList.utils.Resource
import retrofit2.Response
import timber.log.Timber

/**
 * Clase base que contiene la abstraccion de las consultas con retrofit
 * y nos permitira mas adelante reutilizar su logica, optimizando asi
 * la reutilizacion del codigo
 * @author Luis Garcia
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("La conexion a fallado por el siguiente motivo: $message")
    }

}