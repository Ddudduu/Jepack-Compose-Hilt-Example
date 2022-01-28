package com.example.jetpackcomposehiltexample.model.network

sealed class NetworkResult<out T> {
  object Loading : NetworkResult<Nothing>()
  data class Success<out T>(val data: T?) : NetworkResult<T>()
  data class Error(val throwable: Throwable?) : NetworkResult<Nothing>()
}
