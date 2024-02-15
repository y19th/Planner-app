package com.example.util.extension

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Int.toColor(): Color {
    return Color(this)

}

fun Long.toDate(formatPattern: String): String {
    val formatter = SimpleDateFormat(formatPattern, Locale.getDefault())
    val cal = Calendar.getInstance()
    cal.timeInMillis = this
    return formatter.format(cal.time)
}