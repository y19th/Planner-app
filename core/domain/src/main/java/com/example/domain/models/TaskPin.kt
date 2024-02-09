package com.example.domain.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.data.models.TaskPinModel

@Immutable
data class TaskPin(
    val name: String = "pin",
    val importance: Importance = Importance.Medium,
    val containerColor: Color = Color.Transparent,
    val textColor: Color = Color.Black
)

fun TaskPinModel.toTaskPin() = TaskPin(
    name = name,
    importance = Importance.findByValue(valueImportance),
    containerColor = containerColor,
    textColor = textColor
)
