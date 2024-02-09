package com.example.domain.models

import com.example.domain.R

sealed class Importance(val value: Int): Droppable<Importance> {

    companion object {
        fun receiveAll() = listOf(Important,Medium,Low)
    }

    data object Important: Importance(value = 2)

    data object Medium: Importance(value = 1)

    data object Low: Importance(value = 0)

    override fun all(): List<Int> {
        return listOf(
            Important.string(),
            Medium.string(),
            Low.string()
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

    override fun string(): Int {
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

interface Droppable<T> {
    fun string(): Int
    fun all(): List<Int>
    fun find(value: Int): T
}