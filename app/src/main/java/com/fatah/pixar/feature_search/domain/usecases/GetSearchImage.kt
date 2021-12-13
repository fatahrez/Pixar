package com.fatah.pixar.feature_search.domain.usecases

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Pixar
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchImage(
    private val repository: PixarRepository
) {
    operator fun invoke(key: String, search_word: String): Flow<Resource<Pixar>> {
        if (search_word.isBlank()) {
            return flow {  }
        }

        return repository.getSearchImage(key, search_word)
    }
}