package com.example.jetpackcomposehiltexample.repository

import com.example.jetpackcomposehiltexample.api.PhotoApi
import com.example.jetpackcomposehiltexample.model.network.NetworkResult
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class PhotoRepository @Inject constructor(
  private val photoApi: PhotoApi
) {
  fun photoList() = flow {
    emit(NetworkResult.Loading)

    try {
      emit(NetworkResult.Success(photoApi.photos()))
    } catch (e: Exception) {
      Timber.w(e)
      emit(NetworkResult.Error(e))
    }
  }
}