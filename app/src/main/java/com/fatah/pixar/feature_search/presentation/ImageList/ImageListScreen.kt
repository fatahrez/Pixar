package com.fatah.pixar.feature_search.presentation.ImageList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.fatah.pixar.R
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.presentation.BottomContentMenu
import com.fatah.pixar.feature_search.presentation.BottomMenu
import com.fatah.pixar.feature_search.presentation.ImageSearch.GetSearchImageViewModel
import com.fatah.pixar.ui.theme.OrangeButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ImageListScreen() {
    val viewModel: GetTopImagesViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is GetSearchImageViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        HeaderSection()
                        Spacer(modifier = Modifier.height(16.dp))
                        viewModel.showTopImages()
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(modifier = Modifier
                            .height(screenHeight/4)
                        ) {
                            items(state.topImages.size) {i ->
                                val image = state.topImages[i]
                                horizontalListItemSection(hit = image)
                                Spacer(modifier = Modifier.padding(8.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    items(state.topImages.size) {i ->
                        val image = state.topImages[i]
                        VerticalListItemSection(hit = image)
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
        BottomMenu(items = listOf(
            BottomContentMenu("Home", R.drawable.baseline_home_24),
            BottomContentMenu("Search", R.drawable.baseline_search_24),
            BottomContentMenu("profile", R.drawable.baseline_person_24)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Photos",
            style = MaterialTheme.typography.h4
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(50))
        ) {
//            val painter = painterResource(id = R.drawable.avatar)
            Image(
                painter = rememberImagePainter(
                    data = "https://cdn.pixabay.com/user/2021/07/27/14-49-34-818_250x250.jpg"
                ),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun horizontalListItemSection(
    hit: Hit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .height(screenHeight / 4)
            .width(screenWidth / 3)
            .clip(RoundedCornerShape(6))
    ) {
        Image(
            painter = rememberImagePainter(
                data = hit.webformatURL
            ),
            contentDescription = hit.tags,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun VerticalListItemSection(
    hit: Hit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .height(screenHeight / 2)
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(4))
    ) {
        Image(
            painter = rememberImagePainter(
                data = hit.webformatURL
            ), 
            contentDescription = hit.tags,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}