package com.fatah.pixar.feature_search.presentation.ImageDetail

import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.usecases.GetIndividualImage

data class ImageDetailState (
    val individualImage: List<Hit> = emptyList(),
    val isLoading: Boolean = false
)
