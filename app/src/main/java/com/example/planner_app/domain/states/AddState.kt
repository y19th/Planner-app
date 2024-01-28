package com.example.planner_app.domain.states

import androidx.compose.runtime.Immutable
import com.example.planner_app.domain.models.TaskPin

@Immutable
data class AddState(
    val taskTitle: String = "",
    val taskPins: List<TaskPin> = listOf(),
    val taskFrom: String = "",
    val taskTo: String = "",
    val taskEmoji: String = "", /*TODO emoji type?*/
    val taskDescription: String = ""
)
