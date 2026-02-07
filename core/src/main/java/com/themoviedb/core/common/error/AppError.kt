package com.themoviedb.core.common.error

/**
 * Base abstraction for all application-level errors.
 *
 * This class represents **domain and application errors** that are safe to be
 * propagated up to the UI layer.
 *
 * ### Design principles
 * - Does NOT represent loading or process state
 * - Does NOT depend on UI framework
 * - Can be mapped across layers (network → domain → presentation)
 *
 * @property code
 * A stable identifier for the error.
 * This may originate from:
 * - Backend error code
 * - HTTP status code
 * - Frontend-defined business code
 *
 * Used primarily for:
 * - UI decision making
 * - Analytics / logging
 *
 * @property reason
 * A human-readable explanation of the error.
 * Intended for:
 * - Analytics
 * - Debugging
 * - Optional UI display (after mapping)
 */
sealed class AppError(
    open val code: String,
    open val reason: String
)


/**
 * A fallback error used when the exact error type is unknown or cannot be
 * mapped to a more specific [AppError] subtype.
 *
 * This error **must always be safe to display or handle in the UI**.
 *
 * Typical use cases:
 * - Unexpected backend response
 * - Unmapped HTTP status codes
 * - Exceptions thrown by third-party libraries
 * - Defensive fallback during error mapping
 *
 * @property code
 * A best-effort identifier that may come from backend, frontend,
 * or be generated locally.
 *
 * @property reason
 * A descriptive explanation used mainly for analytics and logging.
 *
 * @property throwable
 * The original exception or cause of the error.
 * This should be used for:
 * - Crash reporting
 * - Debug logs
 *
 * ⚠️ Never expose [throwable] directly to the UI.
 */
data class GeneralError(
    override val code: String = "",
    override val reason: String = "",
    val throwable: Throwable? = null,
) : AppError(code, reason)


