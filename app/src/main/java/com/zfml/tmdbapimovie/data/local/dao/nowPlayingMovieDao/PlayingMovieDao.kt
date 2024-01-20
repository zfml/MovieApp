package com.zfml.tmdbapimovie.data.local.dao.nowPlayingMovieDao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.tmdbapimovie.data.local.model.PlayingMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity

@Dao
interface PlayingMovieDao {
    @Query("SELECT * FROM PlayingMovieEntity")
    fun getAllMovies(): PagingSource<Int, PlayingMovieEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMoves(movies: List<PlayingMovieEntity>)

    @Query("SELECT * FROM PlayingMovieEntity WHERE id=:movieId")
    suspend fun getMovieById(movieId: Int): PlayingMovieEntity?

    @Query("DELETE FROM PlayingMovieEntity")
    suspend fun clearMovies()
}