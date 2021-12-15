package com.fatah.pixar.feature_search.domain.usecases

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Hit
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetTopImagesTest {

    @Test
    fun `top images starts with loading RETURN Resource Loading`() = runBlocking{
        val hit = mockk<Hit>()
        val repository = GetSearchImageTest.mockRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(hit, hit, hit)))
        })

        val result = GetTopImages(repository).invoke("key","ec").first()

        assert((result is Resource.Loading))
    }

    @Test
    fun `empty results RETURNS emptyList`() = runBlocking {
        val repository = GetSearchImageTest.mockRepository(flowOf(Resource.Success(emptyList())))

        val result = GetTopImages(repository).invoke("key", "ec")
            .last()

        assert(result.data?.isEmpty() ?: false)
    }

    @Test
    fun `Top images usecase successfully RETURNS Resource Success + Data`() = runBlocking {
        val hit = mockk<Hit>()
        val repository = GetSearchImageTest.mockRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(hit, hit, hit)))
        })

        val result = GetTopImages(repository).invoke("key", "ec")
            .last()

        assert(result is Resource.Success && result.data?.size ?: false == 3)
    }
}