package com.fatah.pixar.feature_search.domain.usecases

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.usecases.GetSearchImageTest.Companion.mockRepository
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetIndividualImageTest {

    @Test
    fun `individual image starts with laoding RETURNS Resource Loading`() = runBlocking {
        val hit = mockk<Hit>()

        val repository = mockRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(hit)))
        })

        val result = GetIndividualImage(repository).invoke("key", "id").first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `Individual Image successfully RETURNS Resource Success + Data`() = runBlocking{
        val hit = mockk<Hit>()

        val repository = mockRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(hit)))
        })

        val result = GetIndividualImage(repository).invoke("key", "id")
            .last()

        assert(result is Resource.Success && (result.data?.size ?: false == 1))
    }

    @Test
    fun `empty results RETURNS emptyList`() = runBlocking {
        val repository = GetSearchImageTest.mockRepository(flowOf(Resource.Success(emptyList())))

        val result = GetIndividualImage(repository).invoke("key", "id")
            .last()

        assert(result.data?.isEmpty() ?: false)
    }
}