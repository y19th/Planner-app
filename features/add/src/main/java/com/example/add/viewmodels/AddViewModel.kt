package com.example.add.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.events.AddEvents
import com.example.domain.states.AddState
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

    private val _state = MutableStateFlow(com.example.domain.states.AddState())
    val state = _state.asStateFlow()


    fun onEvent(event: com.example.domain.events.AddEvents) {
        when(event) {
            is com.example.domain.events.AddEvents.OnChangeTaskTitle -> {
                _state.update {
                    it.copy(taskTitle = event.newValue)
                }
            }
        }
    }
}