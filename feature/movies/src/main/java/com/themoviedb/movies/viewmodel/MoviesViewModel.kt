package com.themoviedb.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.themoviedb.core.data.repository.MovieRepository
import com.themoviedb.core.domain.model.Movie
import com.themoviedb.core.domain.model.Review
import com.themoviedb.core.domain.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Movie>>(UiState.Loading)
    val uiState: StateFlow<UiState<Movie>> = _uiState.asStateFlow()
    fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>> =
        movieRepository.getMoviesByGenre(genreId).cachedIn(viewModelScope)

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = runCatching {
                movieRepository.getMovieDetails(movieId)
            }.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun getReviews(movieId: Int): Flow<PagingData<Review>> =
        movieRepository.getMovieReviews(movieId).cachedIn(viewModelScope)

    fun getTrailerKey(movieId: Int, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val key = runCatching {
                movieRepository.getMovieTrailer(movieId)?.key
            }.getOrNull()

            onResult(key)
        }
    }
}