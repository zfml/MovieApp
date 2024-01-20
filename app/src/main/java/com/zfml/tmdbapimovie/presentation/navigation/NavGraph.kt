package com.zfml.tmdbapimovie.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zfml.tmdbapimovie.presentation.playingMovie.NowPlayingMovieScreen
import com.zfml.tmdbapimovie.presentation.popular.PopularMovieScreen
import com.zfml.tmdbapimovie.presentation.home.HomeScreen

@Composable
fun SetUpNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination =  startDestination,
        ) {
         homeRoute(
             navigateToScreen = {
                 navController.navigate(it) {
                     popUpTo(navController.graph.findStartDestination().id) {
                         saveState = true
                     }
                     launchSingleTop = true
                     restoreState = true
                 }
             }
         )
        popularMoviesRoute()
        nowPlayingMoviesRoute()
    }

}


fun NavGraphBuilder.homeRoute(
    navigateToScreen: (String) -> Unit
) {
    composable(route = Screen.Home.route) {
          HomeScreen()
    }
}

fun NavGraphBuilder.popularMoviesRoute() {
    composable(route = Screen.PopularMovieScreen.route) {
        PopularMovieScreen()
    }
}
fun NavGraphBuilder.nowPlayingMoviesRoute() {
    composable(route = Screen.NowPlayingMovieScreen.route) {
        NowPlayingMovieScreen()
    }
}