package com.fatah.pixar.feature_search.domain.usecases

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetIndividualImage(
    private val repository: PixarRepository
) {
    operator fun invoke(key: String, id: String): Flow<Resource<List<Hit>>> {
        if (id.isBlank()) {
            return flow {  }
        }

        return repository.getIndividualImage(key, id)
    }
}