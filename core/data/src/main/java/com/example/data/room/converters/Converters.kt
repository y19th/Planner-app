package com.example.data.room.converters

import androidx.room.TypeConverter
import com.example.data.models.PinList
import com.example.data.models.TaskPinModel
import com.example.data.models.TaskStatusModel
import com.example.data.models.TaskTimeModel
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromTaskStatus(value: TaskStatusModel): Int = value.ordinal

    @TypeConverter
    fun toTaskStatus(value: Int): TaskStatusModel {
        return TaskStatusModel.entries.find {
            it.ordinal == value
        } ?: TaskStatusModel.IN_PROGRESS
    }

    @TypeConverter
    fun fromTaskPinModel(value: TaskPinModel): String = Gson().toJson(value)

    @TypeConverter
    fun toTaskPinModel(value: String): TaskPinModel =
        Gson().fromJson(value, TaskPinModel::class.java)

    @TypeConverter
    fun fromTaskTimeModel(value: TaskTimeModel): Int {
        return (value.hour * 1000) + value.minute
    }

    @TypeConverter
    fun toTaskTimeModel(value: Int): TaskTimeModel {
        return TaskTimeModel(
            hour = value / 1000,
            minute = (value - (value / 1000))
        )
    }


    @TypeConverter
    fun fromTaskPinListToJSON(list: PinList): String = Gson().toJson(list)

    @TypeConverter
    fun toTaskPinListFromJSON(json: String): PinList = Gson().fromJson(json, PinList::class.java)
}
