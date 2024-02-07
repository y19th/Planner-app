package com.example.domain.events

sealed interface MainEvent {

    data class OnTaskDone(val taskId: Int) : MainEvent
}