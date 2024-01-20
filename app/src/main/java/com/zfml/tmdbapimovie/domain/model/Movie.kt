package com.zfml.tmdbapimovie.domain.model

data class Movie(
    val backdropPath: String,
    val id: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
