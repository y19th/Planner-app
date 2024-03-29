package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.R


val MainFontFamily = FontFamily(
    Font(R.font.rubik_black, weight = FontWeight.Black),
    Font(R.font.rubik_black_italic, style = FontStyle.Italic, weight = FontWeight.Black),
    Font(R.font.rubik_bold, weight = FontWeight.Bold),
    Font(R.font.rubik_bold_italic, style = FontStyle.Italic, weight = FontWeight.Bold),
    Font(R.font.rubik_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.rubik_extrabold_italic, style = FontStyle.Italic, weight = FontWeight.ExtraBold),
    Font(R.font.rubik_italic, style = FontStyle.Italic, weight = FontWeight.Normal),
    Font(R.font.rubik_light, weight = FontWeight.Light),
    Font(R.font.rubik_light_italic, style = FontStyle.Italic, weight = FontWeight.Light),
    Font(R.font.rubik_medium, weight = FontWeight.Medium),
    Font(R.font.rubik_medium_italic, style = FontStyle.Italic , weight = FontWeight.Medium),
    Font(R.font.rubik_regular, weight = FontWeight.Normal),
    Font(R.font.rubik_semibold, weight = FontWeight.SemiBold),
    Font(R.font.rubik_semibold_italic, style = FontStyle.Italic, weight = FontWeight.SemiBold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    displayMedium = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.96.sp
    ),
    labelMedium = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.48.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MainFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    )
)