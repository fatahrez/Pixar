package com.fatah.pixar.feature_search.domain.usecases

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.data.repository.FakePixarRepository
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.repository.PixarRepository


import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetSearchImageTest {

    private lateinit var getSearchImage: GetSearchImage
    private lateinit var fakePixarRepository: FakePixarRepository

//    @Before
//    fun setup() {
//        fakePixarRepository = FakePixarRepository()
//        getSearchImage =GetSearchImage(fakePixarRepository)
//    }

    private fun mockRepository(flowReturn: Flow<Resource<List<Hit>>>) = object :
        PixarRepository {
        override fun getSearchImage(key: String, search_word: String): Flow<Resource<List<Hit>>> =
            flowReturn
        }

    @Test
    fun test_searchStartWithLoading_ResourceLoading() = runBlocking{
        val hit = mockk<Hit>()
        val repository = mockRepository(flowOf(Resource.Loading(listOf(hit, hit, hit))))

        val result = GetSearchImage(repository).invoke("key","yellow flowers").first()

        print(result)
        assert((result is Resource.Loading))
    }
}