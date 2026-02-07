package com.themoviedb.core.data.repository

import com.themoviedb.core.data.mapper.toError
import com.themoviedb.core.data.mapper.toGenre
import com.themoviedb.core.domain.model.Genre
import com.themoviedb.core.domain.model.ResultError
import com.themoviedb.core.network.model.ErrorResponse
import com.themoviedb.core.network.service.TmdbApiService
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Named

class GenreRepositoryImpl @Inject constructor(
    private val apiService: TmdbApiService,
    @Named("tmdb_api_key") private val apiKey: String
) : GenreRepository {

    override suspend fun getMovieGenres(): List<Genre> {
        val response = apiService.getMovieGenres()

        if (!response.isSuccessful) {
            val errorBody = response.errorBody()?.string()
            val resultError: ResultError? = errorBody?.let {
                runCatching { Json.decodeFromString(ErrorResponse.serializer(), it).toError() }
                    .getOrNull()
            }

            throw Exception(
                resultError?.status_message
                    ?: "Failed to fetch genres. Code: ${response.code()}"
            )
        }

        val body = response.body()
            ?: throw Exception("Failed to fetch genres: empty body")

        return body.genres.map { it.toGenre() }
    }
}