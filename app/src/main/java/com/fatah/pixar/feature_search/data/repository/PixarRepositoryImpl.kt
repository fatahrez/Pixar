package com.fatah.pixar.feature_search.data.repository

import com.fatah.pixar.core.util.Resource
import com.fatah.pixar.feature_search.data.local.ImagesDao
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.domain.model.Hit
import com.fatah.pixar.feature_search.domain.model.Pixar
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PixarRepositoryImpl(
    private val api: PixarApi,
    private val dao: ImagesDao
): PixarRepository {
    override fun getSearchImage(key: String, search_word: String): Flow<Resource<List<Hit>>> = flow {
        emit(Resource.Loading())

        val imagesDao = dao.getAllImages().map { it.toHit() }
        emit(Resource.Loading(data = imagesDao))

        try {
            val remoteImages = api.getSearchImage(key, search_word).hits
            dao.deleteImages(remoteImages.map { it.toHitEntity() })
            dao.getSearchedWords(search_word)
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = imagesDao
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server check your internet connection",
                data = imagesDao
            ))
        }
        val finalImagesDao = dao.getSearchedWords(search_word).map { it.toHit() }
        emit(Resource.Success(finalImagesDao))
    }

}