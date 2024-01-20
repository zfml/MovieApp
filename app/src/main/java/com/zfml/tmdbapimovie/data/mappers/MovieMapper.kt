package com.zfml.tmdbapimovie.data.mappers

import com.zfml.tmdbapimovie.data.local.model.PlayingMovieEntity
import com.zfml.tmdbapimovie.data.local.model.PopularMovieEntity
import com.zfml.tmdbapimovie.data.remote.MovieDto
import com.zfml.tmdbapimovie.domain.model.Movie

fun MovieDto.toPopularMovieEntity(): PopularMovieEntity = PopularMovieEntity(
    backdrop_path = backdrop_path ?: "",
    id = id.toString(),
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: -1
)

fun PopularMovieEntity.toMovie(): Movie = Movie(
    backdropPath = backdrop_path,
    id = id,
    overview = overview,
    popularity = popularity,
    posterPath = poster_path,
    releaseDate = release_date,
    title = title,
    voteAverage = vote_average,
    voteCount =  vote_count
)

fun MovieDto.toPlayingMovieEntity(): PlayingMovieEntity = PlayingMovieEntity(
    backdrop_path = backdrop_path ?: "",
    id = id.toString(),
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    poster_path = poster_path ?: "",
    release_date = release_date ?: "",
    title = title ?: "",
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: -1
)

fun PlayingMovieEntity.toMovie(): Movie = Movie(
    backdropPath = backdrop_path,
    id = id,
    overview = overview,
    popularity = popularity,
    posterPath = poster_path,
    releaseDate = release_date,
    title = title,
    voteAverage = vote_average,
    voteCount =  vote_count
)

