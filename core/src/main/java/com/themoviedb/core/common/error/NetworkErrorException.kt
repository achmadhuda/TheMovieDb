package com.themoviedb.core.common.error

/**
 * A [Throwable] wrapper for [NetworkError].
 *
 * Since [NetworkError] does not extend [Throwable], this wrapper is used
 * in contexts that require a [Throwable] (e.g. Paging [LoadResult.Error])
 * while preserving the typed [NetworkError] for UI-layer error differentiation.
 */
class NetworkErrorException(
    val networkError: NetworkError
) : Exception(networkError.reason)