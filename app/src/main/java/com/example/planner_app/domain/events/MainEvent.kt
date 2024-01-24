package com.example.planner_app.domain.events

sealed interface MainEvent {

    data class OnTaskDone(val taskId: Int) : MainEvent
}