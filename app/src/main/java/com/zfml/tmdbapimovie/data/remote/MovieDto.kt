package com.zfml.tmdbapimovie.data.remote

data class MovieDto(
    val backdrop_path: String?,
    val id: Int?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double?,
    val vote_count: Int?
)