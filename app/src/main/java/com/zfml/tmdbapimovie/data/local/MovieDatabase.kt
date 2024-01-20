package com.zfml.tmdbapimovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zfml.tmdbapimovie.data.local.dao.nowPlayingMovieDao.PlayingMovieDao
import com.zfml.tmdbapimovie.data.local.dao.nowPlayingMovieDao.PlayingMovieKeyDao
import com.zfml.tmdbapimovie.data.local.dao.popularMovieDao.PopularMovieDao
import com.zfml.tmdbapimovie.data.local.dao.popularMovieDao.PopularMovieKeysDao
import com.zfml.tmdbapimovie.data.local.model.PlayingMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PlayingMovieKeyEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieKeysEntity

@Database(
    entities = [PopularMovieEntity::class,PopularMovieKeysEntity::class
        ,PlayingMovieEntity::class,PlayingMovieKeyEntity::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {
    abstract val popularMovieDao: PopularMovieDao
    abstract val popularMovieKeysDao: PopularMovieKeysDao

    abstract val playingMovieDao: PlayingMovieDao
    abstract val playingMovieKeysDao: PlayingMovieKeyDao

}