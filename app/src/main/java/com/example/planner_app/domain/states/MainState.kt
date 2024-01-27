package com.example.planner_app.domain.states

import com.example.planner_app.domain.models.TaskModel


data class MainState(
    val isLoading: Boolean = true,
    val taskList: List<TaskModel> = listOf()
)
