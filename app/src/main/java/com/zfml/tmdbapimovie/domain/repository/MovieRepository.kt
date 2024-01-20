package com.zfml.tmdbapimovie.domain.repository

import androidx.paging.PagingData
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import com.zfml.tmdbapimovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllPopularMovies(): Flow<PagingData<Movie>>
    fun getAllPlayingMovies() : Flow<PagingData<Movie>>
}