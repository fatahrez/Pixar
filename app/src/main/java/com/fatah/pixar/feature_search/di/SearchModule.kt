package com.fatah.pixar.feature_search.di

import android.app.Application
import androidx.room.Room
import com.fatah.pixar.feature_search.data.local.PixarDatabase
import com.fatah.pixar.feature_search.data.remote.HttpClient
import com.fatah.pixar.feature_search.data.remote.HttpLogger
import com.fatah.pixar.feature_search.data.remote.PixarApi
import com.fatah.pixar.feature_search.data.repository.PixarRepositoryImpl
import com.fatah.pixar.feature_search.domain.repository.PixarRepository
import com.fatah.pixar.feature_search.domain.usecases.GetIndividualImage
import com.fatah.pixar.feature_search.domain.usecases.GetSearchImage
import com.fatah.pixar.feature_search.domain.usecases.GetTopImages
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun providesGetTopImagesUsecase(repository: PixarRepository): GetTopImages {
        return GetTopImages(repository)
    }

    @Provides
    @Singleton
    fun providesGetIndividualImageUsecase(repository: PixarRepository): GetIndividualImage {
        return GetIndividualImage(repository)
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
    fun providesLoggingInterceptor(): HttpLoggingInterceptor = HttpLogger.create()

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkHttpClient(httpLoggingInterceptor)
    }

    @Provides
    @Singleton
    fun providePixarApi(okHttpClient: OkHttpClient): PixarApi {
        return Retrofit.Builder()
            .baseUrl(PixarApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PixarApi::class.java)
    }
}