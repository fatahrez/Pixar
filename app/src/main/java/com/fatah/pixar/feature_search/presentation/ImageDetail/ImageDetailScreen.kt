package com.fatah.pixar.feature_search.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fatah.pixar.R
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

            Box(modifier = Modifier.fillMaxSize()) {
                if (state.isLoading) Log.i(TAG, "ImageDetailScreen: loading...")
                Image(
                    painter = rememberImagePainter(
                        data = state.individualImage[i].largeImageURL,
                        builder = {
                            placeholder(R.drawable.placeholder)
                        }
                    ),
                    contentDescription = state.individualImage[i].tags,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(screenHeight)
                        .fillMaxWidth()
                )
                Box (
                    modifier = Modifier.align(Alignment.BottomStart)
                ){
                    Column (
                        verticalArrangement = Arrangement.SpaceAround
                            ){
                        Row {

                            Image(
                                painter = rememberImagePainter(
                                    data = state.individualImage[i].userImageURL,
                                    builder = {
                                        placeholder(R.drawable.placeholder)
                                    }
                                ),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(50))
                            )

                            Spacer(modifier = Modifier.size(20.dp))
                            Text(
                                text = state.individualImage[i].user,
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.padding(top = 28.dp)
                            )
                        }

                        Text(
                            text = state.individualImage[i].tags,
                            style = MaterialTheme.typography.h3
                        )

                        Text(
                            text = "liked by you and ${state.individualImage[i].likes} others",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}