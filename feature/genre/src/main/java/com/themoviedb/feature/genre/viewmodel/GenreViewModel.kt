package com.themoviedb.feature.genre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themoviedb.core.domain.model.Genre
import com.themoviedb.core.data.repository.GenreRepository
import com.themoviedb.core.domain.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Genre>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Genre>>> = _uiState.asStateFlow()

    fun getGenres() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            runCatching {
                genreRepository.getMovieGenres()
            }.onSuccess { genres ->
                _uiState.value = UiState.Success(genres)
            }.onFailure { throwable ->
                _uiState.value = UiState.Error(throwable.message.orEmpty())
            }
        }
    }
}