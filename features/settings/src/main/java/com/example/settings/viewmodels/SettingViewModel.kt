package com.example.settings.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.events.SettingEvents
import com.example.domain.states.SettingState
import com.example.domain.usecase.RoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    roomUseCase: RoomUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(tasks = roomUseCase.receiveTasks())
            }
        }
    }


    fun onEvent(event: SettingEvents) {
        when(event) {
            is SettingEvents.OnThemeChange -> {
                _state.update { it.copy(theme = event.newValue) }
            }
        }
    }
}