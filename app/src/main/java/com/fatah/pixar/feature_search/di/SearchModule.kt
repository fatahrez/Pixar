package com.fatah.pixar.feature_search.di

import android.app.Application
import androidx.room.Room
import com.fatah.pixar.feature_search.data.local.PixarDatabase
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.data.repository.PixarRepositoryImpl
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import com.fatah.pixar.feature_search.domain.usecases.GetSearchImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    @Singleton
    fun providesGetSearchImageUsecase(repository: PixarRepository): GetSearchImage {
        return GetSearchImage(repository)
    }

    @Provides
    @Singleton
    fun providesPixarRepository(
        pixarDatabase: PixarDatabase,
        pixarApi: PixarApi
    ): PixarRepository {
        return PixarRepositoryImpl(pixarApi, pixarDatabase.dao)
    }

    @Provides
    @Singleton
    fun providesPixarDatabase(app: Application): PixarDatabase {
        return Room.databaseBuilder(
            app,
            PixarDatabase::class.java,
            "pixar_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePixarApi(): PixarApi {
        return Retrofit.Builder()
            .baseUrl(PixarApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixarApi::class.java)
    }
}