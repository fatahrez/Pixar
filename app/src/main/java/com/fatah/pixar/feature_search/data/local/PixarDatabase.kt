package com.fatah.pixar.feature_search.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fatah.pixar.feature_search.data.local.entity.HitEntity

@Database(
    entities = [HitEntity::class],
    version = 1
)
abstract class PixarDatabase: RoomDatabase() {

    abstract val dao: ImagesDao
}