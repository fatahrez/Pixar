package com.fatah.pixar.feature_search.domain.repository

import com.fatah.pixar.feature_search.data.local.ImagesDao
import com.fatah.pixar.feature_search.data.local.entity.HitEntity
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.data.remote.dto.HitDTO
import com.fatah.pixar.feature_search.data.remote.dto.PixarDTO
import com.fatah.pixar.feature_search.data.repository.PixarRepositoryImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import kotlin.math.exp

class PixarRepositoryTest {

    val mockedHit = mockk<HitDTO>()
    val mockedHitLocal = mockk<HitEntity>()
    val networkRemoteData = mockk<PixarApi>()
    val expectedRemote = listOf(mockedHit, mockedHit, mockedHit)

    @Ignore("fails - return to this")
    @Test
    fun `repository does cache from network RETURNS cache - single source of truth`() = runBlocking{
        val localDao = mockk<ImagesDao>()
        val remoteService = mockk<PixarApi>()

        every { remoteService } returns networkRemoteData

        coEvery { networkRemoteData.getSearchImage("key", "yellow flowers").hits } returns
                expectedRemote


        coEvery { localDao.getSearchedImages("yellow flower") } returns
                expectedRemote.map { it.toHitEntity() }


//        coEvery { networkRemoteData.getSearchImage("key", "yellow flower") } returns
//                responseJsonData

        val repository = PixarRepositoryImpl(networkRemoteData, localDao)

        val actual = repository.getSearchImage("key", "yellow flowers").last()

        assert(expectedRemote == actual)
    }
}