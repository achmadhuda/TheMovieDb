package com.themoviedb.core.data.repository

import com.themoviedb.core.domain.model.Genre

interface GenreRepository {
    suspend fun getMovieGenres(): List<Genre>
}