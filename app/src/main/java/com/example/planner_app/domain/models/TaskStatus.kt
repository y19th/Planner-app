package com.example.planner_app.domain.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
enum class TaskStatus : Parcelable {
    CANCELLED,IN_PROGRESS,COMPLETED
}