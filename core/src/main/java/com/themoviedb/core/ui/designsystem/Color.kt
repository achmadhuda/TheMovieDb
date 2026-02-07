package com.themoviedb.core.ui.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MVColors(
    val transparent: Color,

    // Primary scale
    val primary100: Color,
    val primary200: Color,
    val primary300: Color,
    val primary400: Color,
    val primary500: Color
) {
    companion object {
        @Composable
        fun defaultColors(): MVColors = MVColors(
            transparent = Color(0x00FFFFFF),

            primary100 = Color(0xFFDC4485),
            primary200 = Color(0xFFCA4460),
            primary300 = Color(0xFFED6864),
            primary400 = Color(0xFFE57350),
            primary500 = Color(0xFFFF8A40),
        )
    }
}

internal val LocalColors = compositionLocalOf<MVColors> {
    error("No colors provided! Make sure to wrap all usages in MVTheme.")
}