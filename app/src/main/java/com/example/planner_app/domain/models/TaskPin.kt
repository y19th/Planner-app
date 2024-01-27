package com.example.planner_app.domain.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class TaskPin(
    /*TODO remove color and add importance(or something like that)*/
    val name: String = "pin"
)
