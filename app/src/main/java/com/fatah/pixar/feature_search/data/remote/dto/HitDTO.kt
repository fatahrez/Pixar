package com.fatah.pixar.feature_search.data.remote.dto

import com.fatah.pixar.feature_search.data.local.entity.HitEntity
import com.fatah.pixar.feature_search.domain.model.Hit

data class HitDTO(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
) {
    fun toHit(): Hit {
        return Hit(
            collections = collections,
            comments = comments,
            downloads = downloads,
            id = id,
            imageHeight = imageHeight,
            imageSize = imageSize,
            imageWidth = imageWidth,
            largeImageURL = largeImageURL,
            likes = likes,
            pageURL = pageURL,
            previewHeight = previewHeight,
            previewURL = previewURL,
            previewWidth = previewWidth,
            tags = tags,
            type = type,
            user = user,
            userImageURL = userImageURL,
            user_id = user_id,
            views = views,
            webformatHeight = webformatHeight,
            webformatURL = webformatURL,
            webformatWidth = webformatWidth
        )
    }

    fun toHitEntity(): HitEntity {
        return HitEntity(
            collections = collections,
            comments = comments,
            downloads = downloads,
            id = id,
            imageHeight = imageHeight,
            imageSize = imageSize,
            imageWidth = imageWidth,
            largeImageURL = largeImageURL,
            likes = likes,
            pageURL = pageURL,
            previewHeight = previewHeight,
            previewURL = previewURL,
            previewWidth = previewWidth,
            tags = tags,
            type = type,
            user = user,
            userImageURL = userImageURL,
            user_id = user_id,
            views = views,
            webformatHeight = webformatHeight,
            webformatURL = webformatURL,
            webformatWidth = webformatWidth
        )
    }
}