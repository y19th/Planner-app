package com.example.planner_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.planner_app.domain.events.MainEvent
import com.example.planner_app.domain.states.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    companion object {
        const val TAG = "MainViewModel"
    }

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()


    init {
        _state.update { it.copy(isLoading = true) }
    }


    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnTaskDone -> {

            }
        }
    }

}