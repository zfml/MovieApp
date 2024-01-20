package com.zfml.tmdbapimovie.presentation.playingMovie

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.zfml.tmdbapimovie.presentation.components.MoviePagingListContent
import com.zfml.tmdbapimovie.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingMovieScreen() {
    val viewModel: HomeViewModel = hiltViewModel()

    val allPlayingMovies = viewModel.getAllPlayingMovies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Now Playing Movies") })
        },
        content = {padding ->
            MoviePagingListContent(paddingValues = padding, pagingListItem = allPlayingMovies)
        }
    )



}