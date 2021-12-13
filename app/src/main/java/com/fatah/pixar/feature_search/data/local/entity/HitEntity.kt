package com.fatah.pixar.feature_search.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HitEntity(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    @PrimaryKey val id: Int,
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
)
