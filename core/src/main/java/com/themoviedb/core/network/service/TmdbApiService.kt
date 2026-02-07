package com.themoviedb.core.network.service

import com.themoviedb.core.network.model.GenreResponse
import com.themoviedb.core.network.model.MovieDto
import com.themoviedb.core.network.model.MovieResponse
import com.themoviedb.core.network.model.ReviewResponse
import com.themoviedb.core.network.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDto>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(@Path("movie_id") movieId: Int, @Query("page") page: Int): Response<ReviewResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(@Path("movie_id") movieId: Int): Response<VideoResponse>
}