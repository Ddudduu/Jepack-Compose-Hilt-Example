package com

import android.app.Application
import com.example.jetpackcomposehiltexample.BuildConfig
import com.example.jetpackcomposehiltexample.util.timber.DebugLogTree
import com.kt.safe2go.pass.util.timber.ReleaseLogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(DebugLogTree())
    } else {
      Timber.plant(ReleaseLogTree())
    }
  }
}