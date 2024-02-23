package com.example.domain.states

import androidx.compose.runtime.Stable
import com.example.domain.models.TaskModel
import com.example.domain.models.droppable.Theme

@Stable
data class SettingState(
    val theme: Theme = Theme.Light,
    val tasks: List<TaskModel> = listOf()
)
