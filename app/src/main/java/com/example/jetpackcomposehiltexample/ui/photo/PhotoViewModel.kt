package com.example.jetpackcomposehiltexample.ui.photo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposehiltexample.model.network.NetworkResult
import com.example.jetpackcomposehiltexample.model.network.Photo
import com.example.jetpackcomposehiltexample.repository.PhotoRepository
import com.example.jetpackcomposehiltexample.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
  photoRepository: PhotoRepository
) : BaseViewModel() {
  private val _photos = mutableStateOf(listOf<Photo>())
  val photos: State<List<Photo>> = _photos

  private val photoList = photoRepository.photoList()

  init {
    requestPhoto()
  }

  private fun requestPhoto() {
    viewModelScope.launch {
      photoList.collect { it ->
        when (it) {
          is NetworkResult.Success -> it.data?.let { _photos.value = it }
          else -> {}
        }
      }
    }
  }
}