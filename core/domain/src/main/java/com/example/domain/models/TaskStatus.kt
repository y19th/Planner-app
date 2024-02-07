package com.example.domain.models

import android.os.Parcelable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.data.models.TaskStatusModel
import com.example.domain.R
import com.example.util.extension.success
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
enum class TaskStatus : Parcelable {
    CANCELLED,IN_PROGRESS,COMPLETED;
    fun value(): Int {
        return when(this) {
            CANCELLED -> {
                R.string.status_cancelled
            }
            IN_PROGRESS -> {
                R.string.status_in_progress
            }
            COMPLETED -> {
                R.string.status_completed
            }
        }
    }

    @Composable
    fun color(): Color {
        return when(this) {
            CANCELLED -> {
                MaterialTheme.colorScheme.error
            }
            IN_PROGRESS -> {
                MaterialTheme.colorScheme.primary
            }
            COMPLETED -> {
                MaterialTheme.colorScheme.success
            }
        }
    }
}
fun TaskStatusModel.toTaskStatus() = TaskStatus.valueOf(this.name)