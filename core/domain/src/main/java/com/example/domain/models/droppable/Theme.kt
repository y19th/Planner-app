package com.example.domain.models.droppable

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.domain.R
import com.example.util.Droppable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
sealed class Theme(val value: Int) : Parcelable,Droppable {

    companion object {
        fun receiveAll() = listOf(System,Dark,Light)
    }


    data object System: Theme(0)

    data object Dark: Theme(1)

    data object Light: Theme(2)

    override fun stringId(): Int {
        return when(this) {
            System -> {
               R.string.setting_theme_system
            }
            Dark -> {
                R.string.setting_theme_dark
            }
            Light -> {
                R.string.setting_theme_light
            }
        }
    }

    override fun allIds(): List<Int> {
        return listOf(System.stringId(),Dark.stringId(),Light.stringId())
    }

    override fun find(value: Int): Droppable {
        return when(value) {
            System.value -> {
               System
            }
            Dark.value -> {
                Dark
            }
            Light.value -> {
                Light
            }

            else -> { Light }
        }
    }

}