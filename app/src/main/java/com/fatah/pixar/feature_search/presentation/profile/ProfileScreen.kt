package com.fatah.pixar.feature_search.presentation.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fatah.pixar.R
import com.fatah.pixar.feature_search.presentation.BottomContentMenu
import com.fatah.pixar.feature_search.presentation.BottomMenu
import com.fatah.pixar.feature_search.presentation.ImageList.VerticalListItemSection
import com.fatah.pixar.feature_search.presentation.ImageSearch.GetSearchImageViewModel
import kotlinx.coroutines.flow.collectLatest

private const val TAG = "ProfileScreen"

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val viewModel: ProfileViewModel = hiltViewModel()
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
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!state.isLoading) {
                
                item {
                    Row (
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                            ){
                        Image(
                            painter = rememberImagePainter(
                                data = state.dogImages[1].userImageURL,
                                builder = {
                                    placeholder(R.drawable.placeholder)
                                }
                            ),
                            contentDescription = state.dogImages[1].user,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(150.dp)
                                .clip(RoundedCornerShape(50))
                        )

                        Column (
                            modifier = Modifier
                                .padding(top = 48.dp, start = 16.dp)
                            ){
                            Text(
                                text = "Profile",
                                style = MaterialTheme.typography.subtitle2
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = state.dogImages[1].user,
                                style = MaterialTheme.typography.h5
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Images",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

            }



            items(state.dogImages.size) { i ->
                VerticalListItemSection(
                    hit = state.dogImages[i],
                    navController = navController
                )
            }
        }

        BottomMenu(items =
        listOf(
            BottomContentMenu("Home", R.drawable.baseline_home_24, false),
            BottomContentMenu("Search", R.drawable.baseline_search_24, false),
            BottomContentMenu("Profile", R.drawable.baseline_person_24, true)
        ),
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )
    }
}