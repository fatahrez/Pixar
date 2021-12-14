package com.fatah.pixar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fatah.pixar.feature_search.presentation.ImageSearch.GetSearchImageViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Search() {
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
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search...")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.searchImages.size) {i ->
                        val image = state.searchImages[i]
                        Text(text = image.user)
                    }
                }
            }
        }
    }
}