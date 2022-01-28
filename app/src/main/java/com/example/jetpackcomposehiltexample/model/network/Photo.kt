package com.example.jetpackcomposehiltexample.model.network

import android.graphics.Color
import kotlinx.serialization.Serializable

import kr.susemi99.lgwaterpurifierbyjetpackcompose.model.BaseModel

@Serializable
data class Photo(
  val albumId: Int,
  val id: Int,
  val title: String,
  val url: String,
  val thumbnailUrl: String
) : BaseModel() {
  val colorText = url.split("/").last()
    .chunked(2)
    .map { it.padStart(2, '0') }
    .joinToString("")
    .padEnd(6, '0')

  val color = Color.parseColor("#${colorText}")
}
