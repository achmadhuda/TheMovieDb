package com.themoviedb.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("results")
    val reviews: List<ReviewDto> = emptyList()
)

@Serializable
data class ReviewDto(
    val id: String? = null,
    val author: String? = null,
    val content: String? = null
)