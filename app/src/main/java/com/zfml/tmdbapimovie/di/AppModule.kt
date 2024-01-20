package com.zfml.tmdbapimovie.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.zfml.tmdbapimovie.data.local.MovieDatabase
import com.zfml.tmdbapimovie.data.remote.HeaderInterceptor
import com.zfml.tmdbapimovie.data.remote.MovieApi
import com.zfml.tmdbapimovie.data.repository.MovieRepositoryImpl
import com.zfml.tmdbapimovie.domain.repository.MovieRepository
import com.zfml.tmdbapimovie.util.Constants.BASE_URL
import com.zfml.tmdbapimovie.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(app : Application ): MovieDatabase {
        return Room.databaseBuilder(
            context = app,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val headerInterceptor = HeaderInterceptor()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieApi: MovieApi,movieDatabase: MovieDatabase): MovieRepository =
       MovieRepositoryImpl(movieApi = movieApi, movieDatabase = movieDatabase)





}