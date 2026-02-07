package com.themoviedb.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    @SerialName("genres")
    val genres: List<GenreDto> = emptyList()
)

@Serializable
data class GenreDto(
    val id: Int? = null,
    val name: String? = null
)