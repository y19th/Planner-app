package com.example.domain.events

import androidx.navigation.NavController
import com.example.domain.models.TaskPin
import com.example.domain.models.TaskStatus
import com.example.domain.models.droppable.Filter
import kotlinx.coroutines.Job

sealed interface MainEvent {

    data object OnRefresh: MainEvent

    data object OnEraseStatuses: MainEvent

    data object OnErasePins: MainEvent

    data object OnClearFilter: MainEvent

    data object OnFilterCopy: MainEvent

    data object OnSaveFilter: MainEvent

    data class OnTaskDone(val taskId: Int) : MainEvent

    data class OnTaskChange(val navController: NavController,val taskId: Int): MainEvent

    data class OnTaskDelete(val taskId: Int): MainEvent

    data class OnTaskFilterChanged(val newValue: Filter, val lazyListJob: Job): MainEvent

    data class OnFilterStatusChanged(val clickedChip: TaskStatus, val selectedNow: Boolean): MainEvent

    data class OnFilterPinsChanged(val clickedChip: TaskPin, val selectedNow: Boolean): MainEvent

}