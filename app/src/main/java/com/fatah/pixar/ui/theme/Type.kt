package com.fatah.pixar.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fatah.pixar.R

val sourceSans3Fonts = FontFamily(
    Font(R.font.sourcesans3black, FontWeight.Black),
    Font(R.font.sourcesans3bold, FontWeight.Bold),
    Font(R.font.sourcesans3extrabold, FontWeight.ExtraBold),
    Font(R.font.sourcesans3extralight, FontWeight.ExtraLight),
    Font(R.font.sourcesans3light, FontWeight.Light),
    Font(R.font.sourcesans3extralight, FontWeight.Thin),
    Font(R.font.sourcesans3medium, FontWeight.Medium),
    Font(R.font.sourcesans3regular, FontWeight.Normal),
    Font(R.font.sourcesans3semibold, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
val Typography = Typography(

    h1 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Light,
        fontSize = 105.sp,
        letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Light,
        fontSize = 66.sp,
        letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 52.sp,
        letterSpacing = 0.sp
    ),
    h4 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 37.sp,
        letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    h6 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 0.25.sp
    ),
    button = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
        fontFamily = sourceSans3Fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = 1.5.sp
    )
)