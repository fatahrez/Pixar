package com.fatah.pixar.feature_search.data.repository

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePixarRepository: PixarRepository {
    private val hits = listOf<Hit>()
    override fun getSearchImage(key: String, search_word: String): Flow<Resource<List<Hit>>> {
        return flow{ emit(Resource.Success(hits)) }
    }
}