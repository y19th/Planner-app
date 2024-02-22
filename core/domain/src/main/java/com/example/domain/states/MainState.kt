package com.example.domain.states

import com.example.domain.models.TaskModel
import com.example.domain.models.TaskPin
import com.example.domain.models.TaskStatus
import com.example.domain.models.droppable.Filter
import javax.annotation.concurrent.Immutable


@Immutable
data class MainState(
    val isLoading: Boolean = true,
    val taskList: List<TaskModel> = listOf(),

    val selectedFilter: Filter = Filter.ByName,

    val selectedStatuses: List<TaskStatus> = listOf(),
    val selectedPins: List<TaskPin> = listOf()
)
