package com.themoviedb.core.ui.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Immutable
data class MVGradient(
    val gradient100: List<Color>,
    val overlayVertical: List<Color>
) {
    val gradient200: List<Color> = gradient100.map { it.copy(alpha = 0.1f) }

    fun gradient100(
        widthPx: Float,
        heightPx: Float,
        degree: Float = 140f
    ): Brush {
        val (start, end) = linearGradientOffsets(widthPx, heightPx, degree)
        return Brush.linearGradient(
            colors = gradient100,
            start = start,
            end = end
        )
    }

    fun gradient200(
        widthPx: Float,
        heightPx: Float,
        degree: Float = 140f
    ): Brush {
        val (start, end) = linearGradientOffsets(widthPx, heightPx, degree)
        return Brush.linearGradient(
            colors = gradient200,
            start = start,
            end = end
        )
    }

    fun overlayVertical() = Brush.verticalGradient(overlayVertical)

    private fun linearGradientOffsets(
        width: Float,
        height: Float,
        degree: Float
    ): Pair<Offset, Offset> {
        val rad = (degree - 90f) * (PI.toFloat() / 180f)
        val dx = cos(rad)
        val dy = sin(rad)

        val cx = width / 2f
        val cy = height / 2f

        val halfDiagonal = kotlin.math.sqrt(width * width + height * height) / 2f

        val start = Offset(
            x = cx - dx * halfDiagonal,
            y = cy - dy * halfDiagonal
        )
        val end = Offset(
            x = cx + dx * halfDiagonal,
            y = cy + dy * halfDiagonal
        )
        return start to end
    }
}