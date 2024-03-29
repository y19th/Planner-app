package com.example.domain.events

import androidx.navigation.NavController
import com.example.domain.models.droppable.Importance

sealed interface AddEvents {

    data class OnTaskTitleChange(val newValue: String) : AddEvents

    data class OnDateChange(val newDate: Long): AddEvents

    data class OnTimeFromChange(val newHour: Int, val newMinute: Int): AddEvents

    data class OnTimeToChange(val newHour: Int, val newMinute: Int): AddEvents

    data class OnDescriptionChange(val newDesc: String) : AddEvents

    data class OnPinTitleChange(val newValue: String): AddEvents

    data class OnPinImportanceChange(val newValue: Importance): AddEvents

    data class OnPinColorChange(val newValue: Int): AddEvents

    data class OnNavigateToPin(val pinId: String, val navController: NavController, val taskId: Int) : AddEvents

    data class OnPinAddition(val navController: NavController): AddEvents

    data class OnPinDelete(val pinId: String, val navController: NavController): AddEvents

    data class OnPinUpdate(val pinId: String, val navController: NavController): AddEvents

    data class OnPinNavigateUp(val navController: NavController): AddEvents

    data class OnTaskAdd(val navController: NavController): AddEvents

    data class OnTaskEdit(val taskId: Int): AddEvents

    data class OnEndTaskEdit(val navController: NavController, val taskId: Int) : AddEvents
}
