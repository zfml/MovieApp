package com.zfml.tmdbapimovie.data.Mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zfml.tmdbapimovie.data.local.MovieDatabase
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieKeysEntity
import com.zfml.tmdbapimovie.data.mappers.toPopularMovieEntity
import com.zfml.tmdbapimovie.data.remote.MovieApi

@ExperimentalPagingApi
class PopularMovieRemoteMediator(
    private val movieDatabase: MovieDatabase,
    private val movieApi: MovieApi
): RemoteMediator<Int, PopularMovieEntity>() {
    private val popularMovieDao = movieDatabase.popularMovieDao
    private val popularMovieKeysDao = movieDatabase.popularMovieKeysDao
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMovieEntity>,
    ): MediatorResult{
        return try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys!=null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?:
                    return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = movieApi.getAllPopularMovies(currentPage)

            val movies = response.results


            val endOfPaginationReached = movies.isEmpty()

            val prevPage = if(currentPage == 1 ) null else currentPage - 1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            movieDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    popularMovieDao.clearMovies()
                    popularMovieKeysDao.clearAllPopularRemoteKeys()
                }

                val keys = movies.map { movie ->
                    PopularMovieKeysEntity(
                        id = movie.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                popularMovieKeysDao.addAllPopularMovieRemoteKeys(keys)
                popularMovieDao.insertAllMoves(movies.map { it.toPopularMovieEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PopularMovieEntity>
    ): PopularMovieKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                popularMovieKeysDao.getPopularMoviesRemoteKeyById(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PopularMovieEntity>
    ): PopularMovieKeysEntity? {
        return state.pages.firstOrNull(){it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { movie ->
                popularMovieKeysDao.getPopularMoviesRemoteKeyById(movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PopularMovieEntity>
    ): PopularMovieKeysEntity? {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { movie ->
                popularMovieKeysDao.getPopularMoviesRemoteKeyById(movie.id)
            }
    }


}

