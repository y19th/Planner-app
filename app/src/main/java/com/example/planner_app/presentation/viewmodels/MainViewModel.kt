package com.example.planner_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner_app.domain.events.MainEvent
import com.example.planner_app.domain.states.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    companion object {
        const val TAG = "MainViewModel"
    }

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(1000L)
            _state.update { it.copy(isLoading = false) }
        }
    }


    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnTaskDone -> {

            }
        }
    }

}