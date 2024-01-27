package com.example.planner_app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planner_app.domain.events.MainEvent
import com.example.planner_app.domain.models.TaskModel
import com.example.planner_app.domain.models.TaskStatus
import com.example.planner_app.domain.states.MainState
import com.example.planner_app.domain.usecase.RoomUseCase
import com.example.planner_app.room.entities.TaskEntity
import com.example.planner_app.room.entities.toListTaskModel
import com.example.planner_app.room.schema.MainSchema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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