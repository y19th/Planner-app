package com.example.util.extension

import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

fun Modifier.stateBorder(
    enabled: Boolean = false,
    color: Color,
    shape: Shape,
    width: Dp
) : Modifier {
    return if(enabled) {
        this.border(
            color = color,
            shape = shape,
            width = width
        )
    } else this
}