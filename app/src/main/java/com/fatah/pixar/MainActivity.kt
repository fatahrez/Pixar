package com.fatah.pixar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatah.pixar.feature_search.presentation.GetSearchImageViewModel
import com.fatah.pixar.feature_search.presentation.GetTopImagesViewModel
import com.fatah.pixar.ui.theme.PixarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixarTheme {
                val viewModel: GetTopImagesViewModel = hiltViewModel()
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
//                            TextField(
//                                value = viewModel.searchQuery.value,
//                                onValueChange = viewModel::onSearch,
//                                modifier = Modifier.fillMaxWidth(),
//                                placeholder = {
//                                    Text(text = "Search...")
//                                }
//                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            viewModel.showTopImages()
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(state.topImages.size) {i ->
                                    val image = state.topImages[i]
                                    Log.i(TAG, "onCreate: " + image.user)
                                    Text(text = image.user)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PixarTheme {

    }
}