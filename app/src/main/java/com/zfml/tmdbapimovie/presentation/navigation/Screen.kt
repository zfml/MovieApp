package com.zfml.tmdbapimovie.presentation.navigation

sealed class Screen (val route: String){
    object Home: Screen(route = "home_route")

    object MovieDetail: Screen(route = "movie_detail_route")

    object PopularMovieScreen: Screen(route = "Popular")

    object NowPlayingMovieScreen: Screen(route = "Now Playing")

}