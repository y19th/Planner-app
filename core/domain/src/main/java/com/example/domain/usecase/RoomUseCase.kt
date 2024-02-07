package com.example.domain.usecase

import com.example.data.repository.RoomRepository
import com.example.domain.models.TaskModel
import com.example.domain.models.toTaskModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun receiveTasks() = withContext(defaultDispatcher) {
        repository.receiveTasks().map { entity -> entity.toTaskModel() }
    }

    suspend fun addTask(vararg taskEntity: TaskModel) = withContext(defaultDispatcher) {
        taskEntity.forEach { taskEntity -> repository.addTask(taskEntity.toTaskEntity()) }
    }

    suspend fun updateTask(
        taskId: Int,
        onUpdate: () -> Unit
    ) = withContext(defaultDispatcher) {
        repository.updateTask(
            taskId = taskId,
            onUpdate = onUpdate
        )
    }
}