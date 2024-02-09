package com.example.domain.events

import com.example.domain.models.Importance

sealed interface AddEvents {

    data class OnTaskTitleChange(val newValue: String) : AddEvents

    data class OnDateChange(val newDate: String): AddEvents

    data class OnDescriptionChange(val newDesc: String) : AddEvents

    data class OnPinTitleChange(val newValue: String): AddEvents

    data class OnPinImportanceChange(val newValue: Importance): AddEvents

    data class OnPinColorChange(val newValue: Int): AddEvents
}