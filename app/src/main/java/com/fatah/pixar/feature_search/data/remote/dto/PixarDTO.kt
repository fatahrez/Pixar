package com.fatah.pixar.feature_search.data.remote.dto

import com.fatah.pixar.feature_search.domain.model.Pixar

data class PixarDTO(
    val hits: List<HitDTO>,
    val total: Int,
    val totalHits: Int
) {
    fun toPixar(): Pixar {
        return Pixar(
            hits = hits.map { it.toHit() },
            total = total,
            totalHits = totalHits
        )
    }
}