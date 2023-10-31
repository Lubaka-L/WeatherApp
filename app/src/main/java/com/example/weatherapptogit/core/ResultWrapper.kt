package com.example.weatherapptogit.core

sealed class ResultWrapper<out T> {
    data class Success<T> (val data : T) : ResultWrapper<T>()
    data class Error<T> (val error : T) : ResultWrapper<T>()
    data class Load<T> (val progress : Int) : ResultWrapper<T>()

    companion object {
        fun <T> success(data : T) :ResultWrapper<T> {
            return Success(data)
        }
        fun <T> error(error : T) :ResultWrapper<T> {
            return Error(error)
        }
        fun <T> loading() :ResultWrapper<T> {
            return Load(1)
        }
    }
}

fun <T> ResultWrapper<T>.getSuccess() : T? {
    return if (this is ResultWrapper.Success) return data else null
}