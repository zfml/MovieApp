package com.zfml.tmdbapimovie.data.local.dao.popularMovieDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieKeysEntity

@Dao
interface PopularMovieKeysDao {
    @Query("SELECT * FROM PopularMovieKeysEntity WHERE id=:id")
    suspend fun getPopularMoviesRemoteKeyById(id: String): PopularMovieKeysEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPopularMovieRemoteKeys(remoteKeys:List<PopularMovieKeysEntity>)

    @Query("DELETE FROM PopularMovieKeysEntity")
    suspend fun clearAllPopularRemoteKeys()
}