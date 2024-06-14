package com.example.pruebafrogmi.core.data

sealed class ResponseResult {
    data class Success<T>(val data : T):ResponseResult()
    data class Failed(val error: Throwable):ResponseResult()
}