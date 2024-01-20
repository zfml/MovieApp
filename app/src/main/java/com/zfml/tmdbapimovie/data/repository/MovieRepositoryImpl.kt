package com.zfml.tmdbapimovie.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zfml.tmdbapimovie.data.Mediator.PlayingMovieRemoteMediator
import com.zfml.tmdbapimovie.data.Mediator.PopularMovieRemoteMediator
import com.zfml.tmdbapimovie.data.local.MovieDatabase
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import com.zfml.tmdbapimovie.data.mappers.toMovie
import com.zfml.tmdbapimovie.data.remote.MovieApi
import com.zfml.tmdbapimovie.domain.model.Movie
import com.zfml.tmdbapimovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl(
    private val movieDatabase: MovieDatabase,
    private val movieApi: MovieApi
): MovieRepository {

    override fun getAllPopularMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = {movieDatabase.popularMovieDao.getAllMovies()}
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PopularMovieRemoteMediator(movieDatabase,movieApi),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { it.map { it.toMovie() } }
    }

    override fun getAllPlayingMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = {movieDatabase.playingMovieDao.getAllMovies()}
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PlayingMovieRemoteMediator(movieDatabase,movieApi),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { it.map { it.toMovie() } }
    }

}