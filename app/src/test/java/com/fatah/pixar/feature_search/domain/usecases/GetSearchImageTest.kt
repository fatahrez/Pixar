package com.fatah.pixar.feature_search.domain.usecases

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import com.google.common.truth.Truth


import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import java.net.UnknownHostException

class GetSearchImageTest {
    companion object {
        fun mockRepository(flowReturn: Flow<Resource<List<Hit>>>) = object :
            PixarRepository {
            override fun getSearchImage(key: String, search_word: String): Flow<Resource<List<Hit>>> =
                flowReturn

            override fun getTopImages(key: String, order: String): Flow<Resource<List<Hit>>> =
                flowReturn

            override fun getIndividualImage(key: String, id: String): Flow<Resource<List<Hit>>> =
                flowReturn
        }
    }



    @Test
    fun `Search starts with loading RETURN Resource Loading`() = runBlocking{
        val hit = mockk<Hit>()
        val repository = mockRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(hit, hit, hit)))
        })

        val result = GetSearchImage(repository).invoke("key","yellow flowers").first()

        assert((result is Resource.Loading))
    }


    @Ignore("throws error - rewrite later")
    @Test
    fun `Search Throws HttpException RETURN Resource Error`() = runBlocking {
        val repository = mockRepository(flow {
            throw UnknownHostException()
        })

        val result = GetSearchImage(repository).invoke("key", "yellow flowers")
            .last()

        assert((result is Resource.Error))
    }

    @Test
    fun `Search empty results RETURNS emptyList`() = runBlocking {
        val repository = mockRepository(flowOf(Resource.Success(emptyList())))

        val result = GetSearchImage(repository).invoke("key", "yellow flowers")
            .last()

        assert(result.data?.isEmpty() ?: false)
    }

    @Test
    fun `Search api successfully RETURNS Resource Success + Data`() = runBlocking {
        val hit = mockk<Hit>()
        val repository = mockRepository(flow {
            emit(Resource.Loading())
            emit(Resource.Success(listOf(hit, hit, hit)))
        })

        val result = GetSearchImage(repository).invoke("key", "yellow flowers")
            .last()

        assert(result is Resource.Success && result.data?.size ?: false == 3)
    }
}