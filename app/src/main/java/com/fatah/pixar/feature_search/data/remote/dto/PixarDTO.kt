package com.fatah.pixar.feature_search.data.remote.dto

data class PixarDTO(
    val hits: List<HitDTO>,
    val total: Int,
    val totalHits: Int
)