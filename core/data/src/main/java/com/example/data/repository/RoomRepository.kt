package com.example.data.repository

import com.example.data.room.dao.MainDao
import com.example.data.room.entities.TaskEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val taskDao: MainDao
) {
    fun receiveTasks() = taskDao.getTasks()

    fun addTask(vararg taskEntity: TaskEntity) {
        taskEntity.forEach {
            taskDao.insertTask(it)
        }
    }

    fun deleteTask(vararg taskEntity: TaskEntity) {
        taskEntity.forEach {
            taskDao.deleteTask(it)
        }
    }

    fun updateTask(taskEntity: TaskEntity) {
        taskDao.updateTask(taskEntity)
    }
}