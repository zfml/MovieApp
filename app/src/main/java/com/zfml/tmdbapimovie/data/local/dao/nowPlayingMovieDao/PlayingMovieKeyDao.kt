package com.zfml.tmdbapimovie.data.local.dao.nowPlayingMovieDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.tmdbapimovie.data.local.model.PlayingMovieKeyEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieKeysEntity

@Dao
interface PlayingMovieKeyDao {
    @Query("SELECT * FROM PlayingMovieKeyEntity WHERE id=:id")
    suspend fun getPopularMoviesRemoteKeyById(id: String): PlayingMovieKeyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPopularMovieRemoteKeys(remoteKeys:List<PlayingMovieKeyEntity>)

    @Query("DELETE FROM PlayingMovieKeyEntity")
    suspend fun clearAllPopularRemoteKeys()
}