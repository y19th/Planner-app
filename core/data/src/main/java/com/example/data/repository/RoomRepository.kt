package com.example.data.repository

import com.example.data.room.dao.TaskDao
import com.example.data.room.entities.TaskEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val taskDao: TaskDao
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

    fun receiveTaskById(entityId: Int): TaskEntity? {
        return taskDao.findTaskById(entityId)
    }
}