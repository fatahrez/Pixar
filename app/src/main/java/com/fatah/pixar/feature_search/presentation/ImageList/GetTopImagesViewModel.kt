package com.fatah.pixar.feature_search.presentation.ImageList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.domain.usecases.GetSearchImage
import com.fatah.pixar.feature_search.domain.usecases.GetTopImages
import com.fatah.pixar.feature_search.presentation.ImageSearch.GetSearchImageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GetTopImagesViewModel @Inject constructor(
    private val getTopImages: GetTopImages
): ViewModel(){

    private val _state = mutableStateOf(GetTopImageState())
    val state: State<GetTopImageState> = _state

    private val _eventFlow = MutableSharedFlow<GetSearchImageViewModel.UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        showTopImages()
    }

    fun showTopImages() {
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
                        _eventFlow.emit(
                            GetSearchImageViewModel.UIEvent.ShowSnackbar(
                                result.message ?: "Unknown Error"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}