package com.example.domain.models


data class ElapsedTime(
    val days: Long = 0,
    val hours: Long = 0,
    val minute: Long = 0,
    val seconds: Long = 0
) {
    companion object {
        fun calculateDiffOfModel(model: TaskModel): ElapsedTime {
            var diff = model.dateDay + model.dateTo.toMillis() - System.currentTimeMillis()

            val secInMillis = 1000
            val minInMillis = secInMillis * 60
            val hourInMillis = minInMillis * 60
            val dayInMillis = hourInMillis * 24

            val elapsedDays = diff / dayInMillis
            diff %= dayInMillis

            val elapsedHours = diff / hourInMillis
            diff %= hourInMillis

            val elapsedMinutes = diff / minInMillis
            diff %= minInMillis

            val elapsedSeconds = (diff / secInMillis)

            return ElapsedTime(
                days = elapsedDays,
                hours = elapsedHours,
                minute = elapsedMinutes,
                seconds = elapsedSeconds
            )
        }
    }

    fun isElapsed(): Boolean {
        if(days < 0) return true
        if(hours < 0) return true
        if(minute < 0) return true
        return seconds < 0
    }
}
