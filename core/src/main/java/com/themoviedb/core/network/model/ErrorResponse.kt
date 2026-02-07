package com.themoviedb.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status_code")
    val statusCode: Int?=null,
    @SerialName("status_message")
    val statusMessage: String?=null,
    val success: Boolean?=null
)