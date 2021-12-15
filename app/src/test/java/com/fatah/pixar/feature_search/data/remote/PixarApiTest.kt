package com.fatah.pixar.feature_search.data.remote

import com.google.common.truth.Truth
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader

class PixarApiTest {

    companion object {
        fun jsonToString(path: String): String {
            val resourceStream = javaClass.classLoader?.getResourceAsStream(path)
            val reader = InputStreamReader(resourceStream)
            return reader.use { it.readText() }
        }
    }
    @get:Rule
    val mockServer = MockWebServer()

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixarApi::class.java)
    }

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun `search success RETURNS true`() = runBlocking{
        val response = MockResponse()
            .setBody(jsonToString("ApiResponse.json"))
            .setResponseCode(200)

        mockServer.enqueue(response = response)

        val search = api.getSearchImage("key","yellow flowers")

        Truth.assertThat(search.totalHits).isEqualTo(500)
    }

    @Test
    fun `search success does not return false data RETURNS true`() = runBlocking{
        val response = MockResponse()
            .setBody(jsonToString("ApiResponse.json"))
            .setResponseCode(200)

        mockServer.enqueue(response = response)

        val search = api.getSearchImage("key","yellow flowers")

        Truth.assertThat(search.totalHits).isNotEqualTo(499)
    }

    @Test
    fun `test top images endpoint success number hits RETURNS true`() = runBlocking {
        val response = MockResponse()
            .setBody(jsonToString("ApiResponse.json"))
            .setResponseCode(200)

        mockServer.enqueue(response = response)

        val search = api.getTopImages("key","ec")

        Truth.assertThat(search.totalHits).isEqualTo(500)
    }

    @Test
    fun `top images success does not return false data RETURNS true`() = runBlocking{
        val response = MockResponse()
            .setBody(jsonToString("ApiResponse.json"))
            .setResponseCode(200)

        mockServer.enqueue(response = response)

        val search = api.getTopImages("key","ec")

        Truth.assertThat(search.totalHits).isNotEqualTo(499)
    }
}