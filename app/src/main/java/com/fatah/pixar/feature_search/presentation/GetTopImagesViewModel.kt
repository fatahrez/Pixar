package com.fatah.pixar.feature_search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.domain.usecases.GetTopImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetTopImagesViewModel @Inject constructor(
    private val getTopImages: GetTopImages
): ViewModel(){

    private val _state = mutableStateOf(GetTopImageState())
    val state: State<GetTopImageState> = _state

    private var topImagesJob: Job? = null


    fun showTopImages() {
        topImagesJob = viewModelScope.launch {
            getTopImages(PixarApi.API_KEY, "ec")
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                topImages = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                topImages = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                topImages = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }
}