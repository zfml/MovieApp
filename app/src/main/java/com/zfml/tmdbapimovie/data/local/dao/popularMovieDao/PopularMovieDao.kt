package com.zfml.tmdbapimovie.data.local.dao.popularMovieDao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface PopularMovieDao {
    @Query("SELECT * FROM PopularMovieEntity")
    fun getAllMovies(): PagingSource<Int,PopularMovieEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMoves(movies: List<PopularMovieEntity>)

    @Query("SELECT * FROM POPULARMOVIEENTITY WHERE id=:movieId")
    suspend fun getMovieById(movieId: Int): PopularMovieEntity?

    @Query("DELETE FROM POPULARMOVIEENTITY ")
    suspend fun clearMovies()
}