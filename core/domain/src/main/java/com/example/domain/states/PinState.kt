package com.example.domain.states

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.domain.models.Importance

data class PinState(
    val title: String = "",
    val importance: Importance = Importance.Medium,
    val color: Int = Color.White.toArgb()
)