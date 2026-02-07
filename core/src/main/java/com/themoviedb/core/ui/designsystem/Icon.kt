package com.themoviedb.core.ui.designsystem

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import com.themoviedb.core.R

@Immutable
data class MVIcons(
    @DrawableRes val emptyBox: Int,
) {
    companion object {
        @Composable
        fun defaultIcons(): MVIcons = MVIcons(
            emptyBox = R.drawable.no_image,
        )
    }
}

internal val LocalIcons = compositionLocalOf<MVIcons> {
    error("No icons provided! Make sure to wrap all usages in MVTheme.")
}