package com.themoviedb.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.themoviedb.core.data.mapper.toError
import com.themoviedb.core.data.mapper.toMovie
import com.themoviedb.core.data.mapper.toVideo
import com.themoviedb.core.data.source.MoviePagingSource
import com.themoviedb.core.data.source.ReviewPagingSource
import com.themoviedb.core.domain.model.Movie
import com.themoviedb.core.domain.model.Review
import com.themoviedb.core.domain.model.Video
import com.themoviedb.core.network.model.ErrorResponse
import com.themoviedb.core.network.service.TmdbApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Named

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TmdbApiService,
    @Named("tmdb_api_key") private val apiKey: String
) : MovieRepository {

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { MoviePagingSource(apiService, genreId) }
        ).flow

    override suspend fun getMovieDetails(movieId: Int): Movie {
        val response = apiService.getMovieDetails(movieId)

        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            val resultError = errorBody?.let {
                runCatching { Json.decodeFromString(ErrorResponse.serializer(), it).toError() }
                    .getOrNull()
            }

            throw Exception(resultError?.status_message ?: "Failed to fetch movie details. Code: ${response.code()}")
        }

        val body = response.body()
            ?: throw Exception("Failed to fetch movie details. Empty body")

        return body.toMovie()
    }

    override fun getMovieReviews(movieId: Int): Flow<PagingData<Review>> =
        Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { ReviewPagingSource(apiService, movieId) }
        ).flow

    override suspend fun getMovieTrailer(movieId: Int): Video? =
        runCatching {
            val response = apiService.getMovieVideos(movieId)

            if (!response.isSuccessful) {
                val errorBody = response.errorBody()?.string()
                val resultError = errorBody?.let {
                    runCatching { Json.decodeFromString(ErrorResponse.serializer(), it).toError() }
                        .getOrNull()
                }

                throw Exception(resultError?.status_message ?: "Failed to fetch trailer. Code: ${response.code()}")
            }

            val videos = response.body()?.videos.orEmpty()
            videos
                .firstOrNull { it.site == "YouTube" && it.type.equals("Trailer", ignoreCase = true) }
                ?.toVideo()
        }.getOrNull()
}