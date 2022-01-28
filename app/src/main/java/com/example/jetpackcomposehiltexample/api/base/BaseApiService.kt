package com.example.jetpackcomposehiltexample.api.base

import com.example.jetpackcomposehiltexample.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

open class BaseApiService {
  private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
  }

  open fun baseApi(className: Class<*>): Any {
    val builder = OkHttpClient.Builder()
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)

    // 호출한 정보와 결과 로그를 보고 싶으면 아래 주석 해제해야함
    if (BuildConfig.DEBUG) {
      builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }

    return Retrofit.Builder()
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .baseUrl("https://jsonplaceholder.typicode.com")
      //      .baseUrl("https://api.instantwebtools.net")
      .client(builder.build())
      .build()
      .create(className)
  }
}