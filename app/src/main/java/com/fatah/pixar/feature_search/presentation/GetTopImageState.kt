package com.fatah.pixar.feature_search.presentation

import com.fatah.pixar.feature_search.domain.model.Hit

data class GetTopImageState(
    val topImages: List<Hit> = emptyList(),
    val isLoading: Boolean = false
)