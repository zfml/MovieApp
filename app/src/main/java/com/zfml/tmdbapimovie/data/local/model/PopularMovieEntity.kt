package com.zfml.tmdbapimovie.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PopularMovieEntity(
    val backdrop_path: String,
    @PrimaryKey
    val id: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
