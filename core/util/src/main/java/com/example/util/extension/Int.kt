package com.example.util.extension

import androidx.compose.ui.graphics.Color

fun Int.toColor(): Color {
    return Color(this)
}