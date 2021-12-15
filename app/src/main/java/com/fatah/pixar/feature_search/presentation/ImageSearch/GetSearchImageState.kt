package com.fatah.pixar.feature_search.presentation.ImageSearch

import com.fatah.pixar.feature_search.domain.model.Hit

data class GetSearchImageState(
    val searchImages: List<Hit> = emptyList(),
    val isLoading: Boolean = false
)