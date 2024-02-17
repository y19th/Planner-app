package com.example.domain.states

import com.example.domain.models.TaskModel
import com.example.domain.models.droppable.Filter


data class MainState(
    val isLoading: Boolean = true,
    val taskList: List<TaskModel> = listOf(),

    val selectedFilter: Filter = Filter.ByName
)
