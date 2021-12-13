package com.fatah.pixar.feature_search.data.remote

import com.fatah.pixar.BuildConfig
import com.fatah.pixar.feature_search.data.remote.dto.PixarDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PixarApi {
    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("api?key={key}&q={search_word}")
    suspend fun getSearchImage(
        @Path("key") key: String,
        @Path("search_word") search_word: String
    ): PixarDTO
}