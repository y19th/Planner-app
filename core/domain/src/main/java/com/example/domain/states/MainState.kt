package com.example.domain.states

import com.example.domain.models.TaskModel


data class MainState(
    val isLoading: Boolean = true,
    val taskList: List<TaskModel> = listOf()
)
