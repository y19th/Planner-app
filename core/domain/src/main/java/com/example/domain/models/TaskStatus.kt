package com.example.domain.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.data.models.TaskStatusModel
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
enum class TaskStatus : Parcelable {
    CANCELLED,IN_PROGRESS,COMPLETED
}
fun TaskStatusModel.toTaskStatus() = TaskStatus.valueOf(this.name)