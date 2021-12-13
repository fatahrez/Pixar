package com.fatah.pixar.feature_search.data.repository

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.domain.model.Pixar
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PixarRepositoryImpl(
    private val api: PixarApi
): PixarRepository {
    override fun getSearchImage(key: String, search_word: String): Flow<Resource<Pixar>> = flow{
        emit(Resource.Loading())

    }
}