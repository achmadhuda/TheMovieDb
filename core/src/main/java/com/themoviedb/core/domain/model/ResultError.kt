package com.themoviedb.core.domain.model

data class ResultError(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)