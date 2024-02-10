package com.example.util

interface Droppable {
    fun stringId(): Int
    fun allIds(): List<Int>
    fun find(value: Int): Droppable
}