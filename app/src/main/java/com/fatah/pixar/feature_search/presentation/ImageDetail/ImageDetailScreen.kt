package com.fatah.pixar.feature_search.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.presentation.ImageDetail.ImageDetailViewModel

private const val TAG = "ImageDetailScreen"
@Composable
fun ImageDetailScreen(
    viewModel: ImageDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(state.individualImage.size) {i ->

            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp

            Image(
                painter = rememberImagePainter(
                    data = state.individualImage[i].largeImageURL
                ),
                contentDescription = state.individualImage[i].tags,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(screenHeight)
                    .fillMaxWidth()
            )
            Text(text = state.individualImage[i].user)
        }
    }
}