package com.example.domain.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.data.models.TaskPinModel

@Immutable
data class TaskPin(
    /*TODO remove color and add importance(or something like that)*/
    val name: String = "pin"
)

fun TaskPinModel.toTaskPin() = TaskPin(
    name
)
