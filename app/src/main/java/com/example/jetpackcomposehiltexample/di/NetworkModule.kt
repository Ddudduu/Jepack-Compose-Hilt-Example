package com.example.jetpackcomposehiltexample.di

import com.example.jetpackcomposehiltexample.BuildConfig
import com.example.jetpackcomposehiltexample.api.PhotoApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
  const val BASE_URL = "https://jsonplaceholder.typicode.com"

  @Singleton
  @Provides
  fun providesOkHttpClient() = OkHttpClient.Builder().apply {
    if (BuildConfig.DEBUG) {
      addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
    connectTimeout(15, TimeUnit.SECONDS)
    writeTimeout(15, TimeUnit.SECONDS)
    readTimeout(15, TimeUnit.SECONDS)
  }.build()

  @Provides
  @Singleton
  fun provideConverterFactory(): Json {
    return Json {
      ignoreUnknownKeys = true
      isLenient = true
    }
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
    Retrofit.Builder().apply {
      addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      baseUrl(BASE_URL)
      client(okHttpClient)
    }.build()

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): PhotoApi =
    retrofit.create(PhotoApi::class.java)
}