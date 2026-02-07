package com.themoviedb.core.common.error

import java.net.HttpURLConnection


sealed class NetworkError(override val code: String, override val reason: String): AppError(code, reason)

data class NetworkGeneralError(
    override val code: String = "network-general-error",
    override val reason: String = "",
    val throwable: Throwable? = null,
) : NetworkError(code, reason)

/**
 * Error representing the absence of a valid internet connection.
 *
 * This error indicates that:
 * - The request was NOT sent to the backend
 * - The failure occurred on the client side
 *
 * Typical causes:
 * - No active network connection
 * - Airplane mode
 * - DNS resolution failure
 *
 * @property reason
 * Optional additional context for analytics or debugging.
 */
data class NoInternet(
    override val reason: String = ""
) : NetworkError("no-internet", reason)

/**
 * Errors originating from the **client side of an HTTP request**.
 *
 * These errors indicate that:
 * - The request was successfully sent
 * - The server rejected the request due to client-related issues
 *
 * Typical causes:
 * - Invalid request payload
 * - Missing or malformed parameters
 * - Authentication or authorization issues
 *
 * Client errors are usually mapped from HTTP 4xx status codes.
 */
sealed class ClientError(
    override val code: String,
    override val reason: String
) : NetworkError(code, reason) {

    /**
     * The request was rejected due to invalid or missing input data.
     *
     * This error may contain a backend-provided message describing:
     * - Which field is missing
     * - Which field failed validation
     *
     * @property reason
     * A descriptive message returned by the backend for analytics
     * or detailed UI handling.
     */
    data class BadRequest(
        override val reason: String
    ) : ClientError(
        HttpURLConnection.HTTP_BAD_REQUEST.toString(),
        reason
    )

    /**
     * Authentication failed or credentials are missing.
     *
     * Typically indicates:
     * - Expired session
     * - Invalid access token
     *
     * Usually requires user re-authentication.
     */
    data object Unauthorized : ClientError(
        HttpURLConnection.HTTP_UNAUTHORIZED.toString(),
        "Unauthorized"
    )

    /**
     * The authenticated user does not have permission
     * to access the requested resource.
     */
    data object Forbidden : ClientError(
        HttpURLConnection.HTTP_FORBIDDEN.toString(),
        "Forbidden"
    )

    /**
     * The requested resource does not exist or is no longer available.
     */
    data object NotFound : ClientError(
        HttpURLConnection.HTTP_NOT_FOUND.toString(),
        "Not Found"
    )
}


/**
 * Errors originating from the **server side after a valid request**.
 *
 * These errors indicate that:
 * - The request format was valid
 * - The failure occurred during server processing
 *
 * Typical causes:
 * - Internal server failure
 * - Upstream dependency failure
 * - Temporary service outage
 *
 * Server errors are usually mapped from HTTP 5xx status codes.
 */
sealed class ServerError(
    override val code: String,
    override val reason: String
) : NetworkError(code, reason) {

    /**
     * An unexpected error occurred on the server.
     *
     * This usually indicates a bug or unhandled condition
     * in the backend system.
     */
    data object InternalServerError : ServerError(
        HttpURLConnection.HTTP_INTERNAL_ERROR.toString(),
        "Internal Server Error"
    )

    /**
     * The server received an invalid response from an upstream service.
     */
    data object BadGateway : ServerError(
        HttpURLConnection.HTTP_BAD_GATEWAY.toString(),
        "Bad Gateway"
    )

    /**
     * The server is currently unable to handle the request.
     *
     * This condition is often temporary and may succeed on retry
     */
    data object ServiceUnavailable : ServerError(
        HttpURLConnection.HTTP_UNAVAILABLE.toString(),
        "Service Unavailable"
    )

    /**
     * The server did not receive a timely response from an upstream server.
     */
    data object GatewayTimeout : ServerError(
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT.toString(),
        "Gateway Timeout"
    )
}