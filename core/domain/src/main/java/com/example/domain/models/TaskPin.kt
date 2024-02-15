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

fun TaskPin.toTaskPinModel() = TaskPinModel(
    name = name,
    valueImportance = importance.value,
    containerColor = containerColor,
    textColor = textColor
)

fun List<TaskPin>.toListTaskPinModel() = this.map { model -> model.toTaskPinModel() }

fun List<TaskPinModel>.toListTaskPin() = this.map { entity -> entity.toTaskPin() }
