package com.example.planner_app.domain.events

sealed interface AddEvents {

    data class OnChangeTaskTitle(val newValue: String) : AddEvents
}