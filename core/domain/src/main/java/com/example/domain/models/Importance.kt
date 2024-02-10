package com.example.domain.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.domain.R
import com.example.util.Droppable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
sealed class Importance(val value: Int): Droppable, Parcelable {

    companion object {
        fun receiveAll() = listOf(Important,Medium,Low)

        fun findByValue(value: Int) = Important.find(value)
    }

    data object Important: Importance(value = 2)

    data object Medium: Importance(value = 1)

    data object Low: Importance(value = 0)

    override fun allIds(): List<Int> {
        return listOf(
            Important.stringId(),
            Medium.stringId(),
            Low.stringId()
        )
    }

    override fun find(value: Int): Importance {
        return when {
            Important.value == value -> Important
            Medium.value == value -> Medium
            Low.value == value -> Low
            else -> Medium
        }
    }

    override fun stringId(): Int {
        return when(this) {
            is Important -> {
                R.string.importance_important
            }
            is Medium -> {
                R.string.importance_medium
            }
            is Low -> {
                R.string.importance_low
            }
        }
    }
}