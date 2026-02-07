package com.themoviedb.core.ui.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.themoviedb.core.R

private val Inter = FontFamily(
    Font(R.font.inter_bold, weight = FontWeight.W700),
    Font(R.font.inter_semibold, weight = FontWeight.W600),
    Font(R.font.inter_medium, weight = FontWeight.W500),
    Font(R.font.inter_regular, weight = FontWeight.W400),
)

@Immutable
data class MVTypography(
    val heading1Regular: TextStyle,
    val heading1Medium: TextStyle,
    val heading1SemiBold: TextStyle,
    val heading1Bold: TextStyle,

    val heading2Regular: TextStyle,
    val heading2Medium: TextStyle,
    val heading2SemiBold: TextStyle,
    val heading2Bold: TextStyle,

    val subHeading1Regular: TextStyle,
    val subHeading1Medium: TextStyle,
    val subHeading1SemiBold: TextStyle,
    val subHeading1Bold: TextStyle,

    val subHeading2Regular: TextStyle,
    val subHeading2Medium: TextStyle,
    val subHeading2SemiBold: TextStyle,
    val subHeading2Bold: TextStyle,

    val body1Regular: TextStyle,
    val body1Medium: TextStyle,
    val body1SemiBold: TextStyle,
    val body1Bold: TextStyle,

    val body2Regular: TextStyle,
    val body2Medium: TextStyle,
    val body2SemiBold: TextStyle,
    val body2Bold: TextStyle,

    val captionRegular: TextStyle,
    val captionMedium: TextStyle,
    val captionSemiBold: TextStyle,
    val captionBold: TextStyle,

    val tinyRegular: TextStyle,
    val tinyMedium: TextStyle,
    val tinySemiBold: TextStyle,
    val tinyBold: TextStyle,
) {
    companion object {
        @Composable
        fun defaultTypography(): MVTypography = MVTypography(
            heading1Regular = TextStyle(
                fontFamily = Inter,
                fontSize = 28.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 28.sp * 1.2f
            ),
            heading1Medium = TextStyle(
                fontFamily = Inter,
                fontSize = 28.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 28.sp * 1.2f
            ),
            heading1SemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 28.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 28.sp * 1.2f
            ),
            heading1Bold = TextStyle(
                fontFamily = Inter,
                fontSize = 28.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 28.sp * 1.2f
            ),

            heading2Regular = TextStyle(
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 24.sp * 1.2f
            ),
            heading2Medium = TextStyle(
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 24.sp * 1.2f
            ),
            heading2SemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 24.sp * 1.2f
            ),
            heading2Bold = TextStyle(
                fontFamily = Inter,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 24.sp * 1.2f
            ),

            subHeading1Regular = TextStyle(
                fontFamily = Inter,
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 20.sp * 1.2f
            ),
            subHeading1Medium = TextStyle(
                fontFamily = Inter,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 20.sp * 1.2f
            ),
            subHeading1SemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 20.sp * 1.2f
            ),
            subHeading1Bold = TextStyle(
                fontFamily = Inter,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 20.sp * 1.2f
            ),

            subHeading2Regular = TextStyle(
                fontFamily = Inter,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 18.sp * 1.2f
            ),
            subHeading2Medium = TextStyle(
                fontFamily = Inter,
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 18.sp * 1.2f
            ),
            subHeading2SemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 18.sp * 1.2f
            ),
            subHeading2Bold = TextStyle(
                fontFamily = Inter,
                fontSize = 18.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 18.sp * 1.2f
            ),

            body1Regular = TextStyle(
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 16.sp * 1.5f
            ),
            body1Medium = TextStyle(
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 16.sp * 1.5f
            ),
            body1SemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 16.sp * 1.5f
            ),
            body1Bold = TextStyle(
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 16.sp * 1.5f
            ),

            body2Regular = TextStyle(
                fontFamily = Inter,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 14.sp * 1.5f
            ),
            body2Medium = TextStyle(
                fontFamily = Inter,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 14.sp * 1.5f
            ),
            body2SemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 14.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 14.sp * 1.5f
            ),
            body2Bold = TextStyle(
                fontFamily = Inter,
                fontSize = 14.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 14.sp * 1.5f
            ),

            captionRegular = TextStyle(
                fontFamily = Inter,
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 12.sp * 1.5f
            ),
            captionMedium = TextStyle(
                fontFamily = Inter,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 12.sp * 1.5f
            ),
            captionSemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 12.sp * 1.5f
            ),
            captionBold = TextStyle(
                fontFamily = Inter,
                fontSize = 12.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 12.sp * 1.5f
            ),

            tinyRegular = TextStyle(
                fontFamily = Inter,
                fontSize = 10.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 10.sp * 1.5f
            ),
            tinyMedium = TextStyle(
                fontFamily = Inter,
                fontSize = 10.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 10.sp * 1.5f
            ),
            tinySemiBold = TextStyle(
                fontFamily = Inter,
                fontSize = 10.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 10.sp * 1.5f
            ),
            tinyBold = TextStyle(
                fontFamily = Inter,
                fontSize = 10.sp,
                fontWeight = FontWeight.W700,
                lineHeight = 10.sp * 1.5f
            ),
        )
    }
}

internal val LocalTypography = compositionLocalOf<MVTypography> {
    error("No typography provided! Make sure to wrap all usages in MVTheme.")
}