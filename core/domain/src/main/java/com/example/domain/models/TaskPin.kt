package com.example.domain.models

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.example.data.models.TaskPinModel
import java.util.UUID

@Stable
data class TaskPin(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "pin",
    val importance: Importance = Importance.Medium,
    val containerColor: Color = Color.Transparent,
    val textColor: Color = Color.Black
) {
    companion object {
        const val DefaultId = "DEFAULT"
    }
}

fun TaskPinModel.toTaskPin() = TaskPin(
    name = name,
    importance = Importance.findByValue(valueImportance),
    containerColor = containerColor,
    textColor = textColor
)
