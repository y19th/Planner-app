package com.example.domain.models.droppable

import android.os.Parcelable
import com.example.domain.R
import com.example.util.Droppable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Filter(val value: Int) : Droppable, Parcelable {

    companion object {
        fun receiveAll() = listOf(ByName,ByImportance,ByDeadline)
    }

    data object ByName: Filter(value = 0)

    data object ByImportance: Filter(value = 1)

    data object ByDeadline: Filter(value = 2)


    override fun stringId(): Int {
        return when(this) {
            is ByName -> {
                R.string.filter_by_name
            }
            is ByImportance -> {
                R.string.filter_by_importance
            }
            is ByDeadline -> {
                R.string.filter_by_deadline
            }
        }
    }

    override fun allIds(): List<Int> {
        return listOf(
            ByName.stringId(),
            ByImportance.stringId(),
            ByDeadline.stringId()
        )
    }

    override fun find(value: Int): Droppable {
        return when (value) {
            ByName.value -> ByName
            ByImportance.value -> ByImportance
            ByDeadline.value -> ByDeadline
            else -> ByName
        }
    }
}