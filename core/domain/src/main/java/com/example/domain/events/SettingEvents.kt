package com.example.domain.events

import com.example.domain.models.droppable.Theme

sealed interface SettingEvents {

    data object OnRefreshTasks: SettingEvents
    data class OnThemeChange(val newValue: Theme): SettingEvents
}