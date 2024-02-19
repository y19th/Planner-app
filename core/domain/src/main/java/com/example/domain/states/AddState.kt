package com.example.domain.states

import androidx.compose.runtime.Immutable
import com.example.data.models.TaskTimeModel
import com.example.domain.models.TaskPin

@Immutable
data class AddState(
    val taskTitle: String = "",
    val taskPins: List<TaskPin> = listOf(),
    val taskDate: Long = 0L,
    val taskTimeFrom: TaskTime? = null,
    val taskTimeTo: TaskTime? = null,
    val taskDescription: String = "",


    val isValid: Boolean = false,
    val isUpdated: Boolean = false
)

data class TaskTime(
    val hour: Int = 0,
    val minute: Int = 0
) {
    override fun toString(): String {
        var time = ""
        time += if(hour < 10) "0$hour:" else "$hour:"
        time += if(minute < 10) "0$minute" else minute
        return time
    }

    fun toMillis(): Long {
        return (hour * 3600L + minute * 60L) * 1000L
    }
}

fun TaskTime.toTaskTimeModel() = TaskTimeModel(hour, minute)

fun TaskTimeModel.toTaskTime() = TaskTime(hour, minute)
