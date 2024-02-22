package com.example.domain.states

import androidx.compose.runtime.Immutable
import com.example.domain.models.TaskPin
import com.example.domain.models.TaskStatus

@Immutable
data class FilterState(
    val selectedStatuses: List<TaskStatus> = listOf(),
    val selectedPins: List<TaskPin> = listOf(),
    val allPins: Set<TaskPin> = setOf()
)