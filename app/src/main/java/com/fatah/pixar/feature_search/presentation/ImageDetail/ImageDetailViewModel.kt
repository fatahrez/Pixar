package com.fatah.pixar.feature_search.presentation.ImageDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fatah.pixar.core.util.Constants
import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.domain.usecases.GetIndividualImage
import com.fatah.pixar.feature_search.presentation.ImageSearch.GetSearchImageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val getIndividualImage: GetIndividualImage,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(ImageDetailState())
    val state: State<ImageDetailState> = _state

    private val _eventFlow = MutableSharedFlow<GetSearchImageViewModel.UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>(Constants.IMAGE_ID)?.let {
            showIndividualImage(it)
        }
    }

    fun showIndividualImage(imageId: String) {
        getIndividualImage(PixarApi.API_KEY, imageId)
            .onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            individualImage = result.data ?: emptyList()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            individualImage = result.data ?: emptyList()
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