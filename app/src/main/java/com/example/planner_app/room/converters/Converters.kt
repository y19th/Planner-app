package com.example.planner_app.room.converters

import androidx.room.TypeConverter
import com.example.planner_app.domain.models.TaskPin
import com.example.planner_app.domain.models.TaskStatus
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromTaskStatus(value: TaskStatus): Int = value.ordinal

    @TypeConverter
    fun toTaskStatus(value: Int): TaskStatus {
        return TaskStatus.entries.find {
            it.ordinal == value
        } ?: TaskStatus.IN_PROGRESS
    }

    @TypeConverter
    fun fromTaskPin(value: TaskPin): String = value.name

    @TypeConverter
    fun toTaskPin(value: String): TaskPin = TaskPin(name = value)

    @TypeConverter
    fun fromTaskPinListToJSON(list: PinList): String = Gson().toJson(list)

    @TypeConverter
    fun toTaskPinListFromJSON(json: String): PinList = Gson().fromJson(json, PinList::class.java)
}

data class PinList(
    val list: List<TaskPin> = listOf()
)