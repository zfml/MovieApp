package com.zfml.tmdbapimovie.data.remote

data class MovieResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)