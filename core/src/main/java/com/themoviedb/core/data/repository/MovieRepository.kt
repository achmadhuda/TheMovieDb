package com.themoviedb.core.data.repository

import androidx.paging.PagingData
import com.themoviedb.core.domain.model.Movie
import com.themoviedb.core.domain.model.Review
import com.themoviedb.core.domain.model.Video
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): Movie
    fun getMovieReviews(movieId: Int): Flow<PagingData<Review>>
    suspend fun getMovieTrailer(movieId: Int): Video?
}