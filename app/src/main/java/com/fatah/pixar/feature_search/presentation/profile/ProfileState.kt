package com.fatah.pixar.feature_search.presentation.profile

import com.fatah.pixar.feature_search.domain.model.Hit

data class ProfileState(
    val dogImages: List<Hit> = emptyList(),
    val isLoading: Boolean = false
)
