package com.themoviedb.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    @SerialName("results")
    val videos: List<VideoDto> = emptyList()
)

@Serializable
data class VideoDto(
    val id: String? = null,
    val key: String? = null,
    val name: String? = null,
    val site: String? = null,
    val type: String? = null
)