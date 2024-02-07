package com.example.domain.events

sealed interface AddEvents {

    data class OnChangeTaskTitle(val newValue: String) : AddEvents
}