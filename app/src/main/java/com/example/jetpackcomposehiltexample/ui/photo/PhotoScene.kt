package com.example.jetpackcomposehiltexample.ui.photo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter

@Composable
fun PhotoScene() {
  val vm = viewModel<PhotoViewModel>()
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    LazyColumn(
      modifier = Modifier
        .fillMaxHeight()
        .width(400.dp)
        .padding(30.dp)
        .background(Color.Black),
      verticalArrangement = Arrangement.spacedBy(12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      items(vm.photos.value.toList()) { photo ->
        Row {
          Text(text = photo.colorText, color = Color(photo.color))
          Spacer(modifier = Modifier.width(10.dp))
          Box(
            Modifier
              .size(40.dp)
              .background(color = Color(photo.color))
          ) {
          }
          Spacer(modifier = Modifier.width(10.dp))
          Image(
            painter = rememberImagePainter(data = photo.thumbnailUrl,
              builder = { crossfade(true) }),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 80.dp, height = 40.dp),
          )
        }
        ListDivider()
      }
    }
  }
}

@Composable
fun ListDivider() {
  Divider(
    modifier = Modifier
      .height(1.dp)
      .fillMaxWidth()
      .padding(start = 20.dp, end = 20.dp)
      .background(Color.White)
  )
}