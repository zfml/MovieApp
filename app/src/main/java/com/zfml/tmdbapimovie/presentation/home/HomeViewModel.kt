package com.zfml.tmdbapimovie.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.zfml.tmdbapimovie.data.mappers.toMovie
import com.zfml.tmdbapimovie.domain.model.Movie
import com.zfml.tmdbapimovie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
     movieRepository: MovieRepository
): ViewModel() {

    val getAllPopularMovies: Flow<PagingData<Movie>> = movieRepository.getAllPopularMovies().cachedIn(viewModelScope)
    val getAllPlayingMovies: Flow<PagingData<Movie>> = movieRepository.getAllPlayingMovies().cachedIn(viewModelScope)


}