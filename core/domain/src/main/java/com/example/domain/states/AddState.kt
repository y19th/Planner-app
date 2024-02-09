package com.example.domain.states

import androidx.compose.runtime.Immutable
import com.example.domain.models.TaskPin

@Immutable
data class AddState(
    val taskTitle: String = "",
    val taskPins: List<TaskPin> = listOf(),
    val taskDate: String = "",
    val taskTimeFrom: String = "",
    val taskTimeTo: String = "",
    val taskDescription: String = "",


    val isValid: Boolean = false
)
