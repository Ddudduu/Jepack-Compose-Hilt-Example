package com.example.jetpackcomposehiltexample.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
//  protected val disposableBag = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
//    disposableBag.clear()
  }
}