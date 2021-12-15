package com.fatah.pixar.feature_search.domain.model

data class Pixar(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)