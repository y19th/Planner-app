package com.example.planner_app.domain.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color


@Immutable
data class TaskColors(
    val container: Color,
    val onContainer: Color,
    val pin: Color,
    val onPin: Color,
    val done: Color? = null,
    val onDone: Color? = null,
    val cancel: Color? = null,
    val onCancel: Color? = null
)