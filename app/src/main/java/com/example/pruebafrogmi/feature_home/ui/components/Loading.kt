package com.example.pruebafrogmi.feature_home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.pruebafrogmi.R
import com.example.pruebafrogmi.ui.theme.Negro2

@Composable
fun LoadingImage(){

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components { add(GifDecoder.Factory()) }
        .build()

    Surface(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        color = Negro2.copy(alpha = 0.6f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentHeight()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = R.drawable.gifload)
                        .build(),
                    imageLoader = imageLoader
                ),
                contentDescription = "empty data",
                modifier = Modifier.size(140.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}