package com.example.domain.states

import android.os.Parcelable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.domain.models.droppable.Importance
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class PinState(
    val title: String = "",
    val importance: Importance = Importance.Medium,
    val backgroundColor: Int = Color.Unspecified.toArgb(),
    val textColor: Int = Color.Unspecified.toArgb()
) : Parcelable