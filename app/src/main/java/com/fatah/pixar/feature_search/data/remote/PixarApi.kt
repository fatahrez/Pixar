package com.fatah.pixar.feature_search.data.remote

import com.fatah.pixar.BuildConfig
import com.fatah.pixar.feature_search.data.remote.dto.PixarDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PixarApi {
    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("api")
    suspend fun getSearchImage(
        @Query("key") key: String,
        @Query("q") search_word: String
    ): PixarDTO

    @GET("api")
    suspend fun getTopImages(
        @Query("key") key: String,
        @Query("order") order: String
    ): PixarDTO

    @GET("api")
    suspend fun getIndividualImage(
        @Query("key") key: String,
        @Query("id") id: String
    ): PixarDTO
}