package com.fatah.pixar.feature_search.domain.repository

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Pixar
import kotlinx.coroutines.flow.Flow

interface PixarRepository {

    fun getSearchImage(key: String): Flow<Resource<Pixar>>

}