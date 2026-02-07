package com.themoviedb.core.ui.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

@Composable
fun MVTheme(
    colors: MVColors = MVColors.defaultColors(),
    typography: MVTypography = MVTypography.defaultTypography(),
    icons: MVIcons = MVIcons.defaultIcons(),
    content: @Composable () -> Unit
) {

    val currentDensity = LocalDensity.current
    val cappedDensity = Density(
        density = currentDensity.density,
        fontScale = currentDensity.fontScale.coerceAtMost(1.0f)
    )

    CompositionLocalProvider(
        LocalDensity provides cappedDensity,
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalIcons provides icons
    ) {
        content()
    }
}

object MVTheme {
    val colors: MVColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: MVTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val icons: MVIcons
        @Composable
        @ReadOnlyComposable
        get() = LocalIcons.current
}