package com.example.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.events.MainEvent
import com.example.domain.models.TaskStatus
import com.example.domain.states.MainState
import com.example.domain.usecase.RoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val roomUseCase: RoomUseCase
) : ViewModel() {
    companion object {
        const val TAG = "MainViewModel"
    }

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                taskList = roomUseCase.receiveTasks(),
                isLoading = false
            ) }
        }
    }


    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnTaskDone -> {
                viewModelScope.launch {
                    roomUseCase.updateTask(
                        taskId = event.taskId,
                        onUpdate = {
                            _state.update {
                                it.copy(
                                    taskList = state.value.taskList.map { model ->
                                        if(event.taskId == model.id) {
                                            model.copy(status = TaskStatus.COMPLETED)
                                        }
                                        else model
                                    }
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}