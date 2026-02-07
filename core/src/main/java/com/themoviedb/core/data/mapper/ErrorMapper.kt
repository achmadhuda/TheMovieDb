package com.themoviedb.core.data.mapper

import com.themoviedb.core.domain.model.ResultError
import com.themoviedb.core.network.model.ErrorResponse

fun ErrorResponse.toError(): ResultError {
    return ResultError(
        status_code = statusCode ?: 0,
        status_message = statusMessage.orEmpty(),
        success = success ?: false
    )
}