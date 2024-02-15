package com.example.add.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.events.AddEvents
import com.example.domain.models.Importance
import com.example.domain.models.TaskModel
import com.example.domain.models.TaskPin
import com.example.domain.models.nav.Routes
import com.example.domain.states.AddState
import com.example.domain.states.PinState
import com.example.domain.states.TaskTime
import com.example.domain.usecase.RoomUseCase
import com.example.util.extension.adaptive
import com.example.util.extension.toColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val roomUseCase: RoomUseCase
) : ViewModel() {

    companion object {
        const val TAG = "AddViewModel"
    }

    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()

    private val _pinState = MutableStateFlow(PinState())
    val pinState = _pinState.asStateFlow()


    fun onEvent(event: AddEvents) {
        when(event) {
            is AddEvents.OnTaskTitleChange -> {
                _state.update { it.copy(taskTitle = event.newValue) }
                updateValid()
            }

            is AddEvents.OnDateChange -> {
                _state.update { it.copy(taskDate = event.newDate) }
                updateValid()
            }

            is AddEvents.OnTimeFromChange -> {
                _state.update { it.copy(
                    taskTimeFrom = TaskTime(hour = event.newHour, minute = event.newMinute)
                ) }
                updateValid()
            }

            is AddEvents.OnTimeToChange -> {
                _state.update { it.copy(
                    taskTimeTo = TaskTime(hour = event.newHour, minute = event.newMinute)
                ) }
                updateValid()
            }

            is AddEvents.OnDescriptionChange -> {
                _state.update { it.copy(taskDescription = event.newDesc) }
                updateValid()
            }

            is AddEvents.OnPinTitleChange -> {
                _pinState.update { it.copy(title = event.newValue) }
            }

            is AddEvents.OnPinImportanceChange -> {
                _pinState.update { it.copy(importance = event.newValue) }
            }

            is AddEvents.OnPinColorChange -> {
                _pinState.update {
                    it.copy(
                        backgroundColor = event.newValue,
                        textColor = Color.adaptive(event.newValue.toColor()).toArgb()
                    )
                }
            }

            is AddEvents.OnNavigateToPin -> {
                event.navController.navigate(Routes.ADD.routeWith(event.pinId))
            }

            is AddEvents.OnPinAddition -> {
                with(state.value) {
                    _state.update {
                        it.copy(
                            taskPins = taskPins.plus(
                                TaskPin(
                                    name = pinState.value.title,
                                    importance = pinState.value.importance,
                                    containerColor = pinState.value.backgroundColor.toColor(),
                                    textColor = pinState.value.textColor.toColor()
                                )
                            )
                        )
                    }
                    event.navController.navigateUp()
                    pinStateToDefault()
                }
            }
            is AddEvents.OnPinUpdate -> {
                val mappedList = state.value.taskPins.map { pin ->
                    return@map if(pin.id == event.pinId) pin.copy(
                        name = pinState.value.title,
                        containerColor = pinState.value.backgroundColor.toColor(),
                        importance = pinState.value.importance,
                        textColor = pinState.value.textColor.toColor()
                    ) else pin
                }

                _state.update {
                    it.copy(
                        taskPins = mappedList
                    )
                }
                event.navController.navigateUp()
                pinStateToDefault()
            }
            is AddEvents.OnPinNavigateUp -> {
                event.navController.navigateUp()
                pinStateToDefault()
            }
            is AddEvents.OnPinDelete -> {
                _state.update {
                    it.copy(
                        taskPins = state.value.taskPins.filterNot { pin -> pin.id == event.pinId }
                    )
                }
                event.navController.navigateUp()
                pinStateToDefault()
            }
            is AddEvents.OnTaskAdd -> {
                viewModelScope.launch {
                    roomUseCase.addTask(
                        TaskModel(
                            title = state.value.taskTitle,
                            content = state.value.taskDescription,
                            dateFrom = state.value.taskTimeFrom ?: TaskTime(),
                            dateTo = state.value.taskTimeTo ?: TaskTime(),
                            taskPin = state.value.taskPins
                        )
                    )
                }.invokeOnCompletion {
                    event.navController.navigateUp()
                    _state.update { AddState() }
                }
            }
        }
    }

    fun setPinState(pinId: String) {
        val found = state.value.taskPins.find { pin -> pin.id == pinId }
        found?.let { pin ->
            _pinState.update {
                it.copy(
                    title = pin.name,
                    importance = pin.importance,
                    backgroundColor = pin.containerColor.toArgb(),
                    textColor = pin.textColor.toArgb()
                )
            }
        }
    }

    private fun pinStateToDefault() {
        _pinState.update {
            it.copy(
                title = "",
                importance = Importance.Medium,
                backgroundColor = Color.Unspecified.toArgb()
            )
        }
    }

    private fun updateValid() {
        val isValid = checkValidFields()
        if(isValid != state.value.isValid) {
            _state.update { it.copy(isValid = isValid) }
        }
    }

    private fun checkValidFields(): Boolean {
        with(state.value) {
            return taskTitle.isNotEmpty() && taskDescription.isNotEmpty() && taskDate.isNotEmpty()
                    && taskTimeFrom != null && taskTimeTo != null
        }
    }

}