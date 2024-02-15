package com.example.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class TaskTimeModel(
    val hour: Int = 0,
    val minute: Int = 0
)

