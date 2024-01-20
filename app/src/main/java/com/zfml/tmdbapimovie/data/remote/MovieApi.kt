package com.zfml.tmdbapimovie.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("popular")
    suspend fun getAllPopularMovies(
        @Query("page") page: Int
    ): MovieResponse

    @GET("now_playing")
    suspend fun getAllPlayingMovies(
        @Query("page") page: Int
    ): MovieResponse

}