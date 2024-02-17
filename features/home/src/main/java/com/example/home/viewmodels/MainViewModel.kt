package com.example.home.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.events.MainEvent
import com.example.domain.models.TaskModel
import com.example.domain.models.TaskStatus
import com.example.domain.models.droppable.Filter
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


    fun onEvent(event: MainEvent) {
        when(event) {
            is MainEvent.OnTaskDone -> {
                viewModelScope.launch {
                    state.value.taskList.find {model ->
                        model.id == event.taskId
                    }?.let { foundModel ->
                        roomUseCase.updateTask(foundModel)
                        _state.update {
                            it.copy(
                                taskList = state.value.taskList.map { model ->
                                    if(model.id == foundModel.id) {
                                        foundModel.copy(status = TaskStatus.COMPLETED)
                                    } else model
                                }
                            )
                        }
                    }
                }
            }
            is MainEvent.OnRefresh -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        taskList = roomUseCase.receiveTasks(),
                        isLoading = false
                    ) }
                }
            }
            is MainEvent.OnTaskChange -> {

            }
            is MainEvent.OnTaskDelete -> {
                viewModelScope.launch {
                    state.value.taskList.find {
                        model -> model.id == event.taskId
                    }?.let { model ->
                        roomUseCase.deleteTask(model)
                        _state.update { oldState ->
                            oldState.copy(
                                taskList = state.value.taskList.filter {
                                    task -> task.id != model.id
                                }
                            )
                        }
                    }
                }
            }
            is MainEvent.OnTaskFilterChanged -> {
                _state.update {
                    it.copy(
                        selectedFilter = event.newValue,
                        taskList = sortByFilter(selectedFilter = event.newValue)
                    )
                }
            }
        }
    }

    private fun sortByFilter(selectedFilter: Filter): List<TaskModel> {
        return when(selectedFilter) {
            is Filter.ByName -> {
                state.value.taskList.sortedBy { taskModel -> taskModel.title }
            }
            is Filter.ByImportance -> {
                state.value.taskList.sortedByDescending { taskModel ->
                    taskModel.taskPin.sumOf { pin -> pin.importance.value }
                }
            }
            else -> {
                state.value.taskList.sortedBy { taskModel -> taskModel.id }
            }
        }
    }
}