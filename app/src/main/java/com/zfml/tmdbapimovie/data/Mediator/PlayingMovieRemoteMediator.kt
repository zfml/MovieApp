package com.zfml.tmdbapimovie.data.Mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.zfml.tmdbapimovie.data.local.MovieDatabase
import com.zfml.tmdbapimovie.data.local.model.PlayingMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PlayingMovieKeyEntity
import com.zfml.tmdbapimovie.data.mappers.toPlayingMovieEntity
import com.zfml.tmdbapimovie.data.remote.MovieApi

@ExperimentalPagingApi
class PlayingMovieRemoteMediator(
    private val movieDatabase: MovieDatabase,
    private val movieApi: MovieApi
): RemoteMediator<Int, PlayingMovieEntity>() {
    private val playingMovieDao = movieDatabase.playingMovieDao
    private val playingMovieKeysDao = movieDatabase.playingMovieKeysDao
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlayingMovieEntity>,
    ): MediatorResult {
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

            val response = movieApi.getAllPlayingMovies(currentPage)

            val movies = response.results


            val endOfPaginationReached = movies.isEmpty()

            val prevPage = if(currentPage == 1 ) null else currentPage - 1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            movieDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    playingMovieDao.clearMovies()
                    playingMovieKeysDao.clearAllPopularRemoteKeys()
                }

                val keys = movies.map { movie ->
                    PlayingMovieKeyEntity(
                        id = movie.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                playingMovieKeysDao.addAllPopularMovieRemoteKeys(keys)
                playingMovieDao.insertAllMoves(movies.map { it.toPlayingMovieEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PlayingMovieEntity>
    ): PlayingMovieKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                playingMovieKeysDao.getPopularMoviesRemoteKeyById(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, PlayingMovieEntity>
    ): PlayingMovieKeyEntity? {
        return state.pages.firstOrNull(){it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { movie ->
                playingMovieKeysDao.getPopularMoviesRemoteKeyById(movie.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PlayingMovieEntity>
    ): PlayingMovieKeyEntity? {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { movie ->
                playingMovieKeysDao.getPopularMoviesRemoteKeyById(movie.id)
            }
    }


}
