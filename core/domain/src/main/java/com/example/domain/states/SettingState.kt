package com.example.domain.states

import com.example.domain.models.TaskModel
import com.example.domain.models.droppable.Theme

data class SettingState(
    val theme: Theme = Theme.Light,
    val tasks: List<TaskModel> = listOf()
)
