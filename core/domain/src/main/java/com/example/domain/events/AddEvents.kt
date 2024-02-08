package com.example.domain.events

sealed interface AddEvents {

    data class OnChangeTaskTitle(val newValue: String) : AddEvents

    data class OnDateChange(val newDate: String): AddEvents

    data class OnDescriptionChange(val newDesc: String) : AddEvents
}