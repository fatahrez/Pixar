package com.fatah.pixar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.presentation.BottomContentMenu
import com.fatah.pixar.feature_search.presentation.BottomMenu
import com.fatah.pixar.feature_search.presentation.ImageSearch.GetSearchImageViewModel
import kotlinx.coroutines.flow.collectLatest

@ExperimentalFoundationApi
@Composable
fun SearchScreen(
    navController: NavController
) {
    val viewModel: GetSearchImageViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

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

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = viewModel.searchQuery.value,
                        onValueChange = viewModel::onSearch,
                        placeholder = {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Search")

                                val painter = painterResource(id = R.drawable.baseline_search_24)
                                Icon(painter = painter, contentDescription = "search")
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(state.searchImages.size) {i ->
                            val image = state.searchImages[i]
                            SearchResultGrid(hit = image)
                        }
                    }
                }
            }
        }

        BottomMenu(items =
            listOf(
                BottomContentMenu("Home", R.drawable.baseline_home_24, false),
                BottomContentMenu("Search", R.drawable.baseline_search_24, true),
                BottomContentMenu("profile", R.drawable.baseline_person_24, false)
            ),
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun SearchResultGrid(
    hit: Hit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = rememberImagePainter(
                data = hit.webformatURL
            ),
            contentDescription = hit.tags,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}