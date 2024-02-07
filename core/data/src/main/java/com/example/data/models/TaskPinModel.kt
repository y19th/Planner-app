package com.example.data.models

import androidx.compose.runtime.Immutable

@Immutable
data class TaskPinModel(
    /*TODO remove color and add importance(or something like that)*/
    val name: String = "pin"
)