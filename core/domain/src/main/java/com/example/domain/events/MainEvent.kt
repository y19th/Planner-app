package com.example.domain.events

import androidx.navigation.NavController
import com.example.domain.models.droppable.Filter

sealed interface MainEvent {

    data object OnRefresh: MainEvent

    data class OnTaskDone(val taskId: Int) : MainEvent

    data class OnTaskChange(val navController: NavController,val taskId: Int): MainEvent

    data class OnTaskDelete(val taskId: Int): MainEvent

    data class OnTaskFilterChanged(val newValue: Filter): MainEvent
}