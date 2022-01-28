package com.example.jetpackcomposehiltexample.api

import com.example.jetpackcomposehiltexample.model.network.Photo
import retrofit2.http.GET

interface PhotoApi {
  @GET("photos")
  suspend fun photos(): List<Photo>
}