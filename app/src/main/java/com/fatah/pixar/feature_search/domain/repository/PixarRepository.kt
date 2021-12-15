package com.fatah.pixar.feature_search.domain.repository

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.model.Pixar
import kotlinx.coroutines.flow.Flow

interface PixarRepository {

    fun getSearchImage(key: String, search_word: String): Flow<Resource<List<Hit>>>

    fun getTopImages(key: String, order: String): Flow<Resource<List<Hit>>>

    fun getIndividualImage(key: String, id: String): Flow<Resource<List<Hit>>>
}