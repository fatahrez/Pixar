package com.fatah.pixar.feature_search.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fatah.pixar.feature_search.data.local.entity.HitEntity

@Dao
interface ImagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<HitEntity>)

    @Query("DELETE FROM hitentity")
    suspend fun deleteImages()

    @Query("SELECT * FROM hitentity WHERE tags LIKE '%' || :search_word || '%'")
    suspend fun getSearchedImages(search_word: String): List<HitEntity>

    @Query("SELECT * FROM hitentity")
    suspend fun getAllImages(): List<HitEntity>

    @Query("SELECT * FROM hitentity WHERE id = :id")
    suspend fun getIndividualImage(id: String): List<HitEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIndividualImage(images: List<HitEntity>)

    @Query("DELETE FROM hitentity WHERE id=:id")
    suspend fun deleteIndividualImage(id: String)
}