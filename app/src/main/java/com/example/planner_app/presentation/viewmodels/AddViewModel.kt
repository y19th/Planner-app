package com.example.planner_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.planner_app.domain.events.AddEvents
import com.example.planner_app.domain.states.AddState
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


    fun onEvent(event: AddEvents) {
        when(event) {
            is AddEvents.OnChangeTaskTitle -> {
                _state.update {
                    it.copy(taskTitle = event.newValue)
                }
            }
        }
    }
}