package com.example.add.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.events.AddEvents
import com.example.domain.models.Importance
import com.example.domain.models.TaskPin
import com.example.domain.models.nav.Routes
import com.example.domain.states.AddState
import com.example.domain.states.PinState
import com.example.util.extension.toColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor() : ViewModel() {

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
                _pinState.update { it.copy(color = event.newValue) }
            }

            is AddEvents.OnNavigateToPin -> {
                event.navController.navigate(Routes.ADD.routeWith(Routes.PIN.name))
            }

            is AddEvents.OnPinAddition -> {
                with(state.value) {
                    _state.update {
                        it.copy(
                            taskPins = taskPins.plus(
                                TaskPin(
                                    name = pinState.value.title,
                                    importance = pinState.value.importance,
                                    containerColor = pinState.value.color.toColor()
                                )
                            )
                        )
                    }
                    _pinState.update {
                        it.copy(
                            title = "",
                            importance = Importance.Medium
                        )
                    }
                    event.navController.navigateUp()
                }
            }
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
        }
    }

}