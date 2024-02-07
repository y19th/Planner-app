package com.example.data.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class TaskPinModel(
    val name: String = "pin",
    val containerColor: Color = Color.Transparent,
    val textColor: Color = Color.Black

)