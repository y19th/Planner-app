package com.example.settings.models

import androidx.annotation.StringRes
import com.example.ui.R

sealed class ThemeSetting(@StringRes val nameId: Int) {
    companion object {
        fun all(): List<ThemeSetting> {
            return listOf(
                Dark,
                System,
                Light
            )
        }
    }
    data object Light: ThemeSetting(nameId = R.string.setting_light_theme)

    data object Dark: ThemeSetting(nameId = R.string.setting_dark_theme)

    data object System: ThemeSetting(nameId = R.string.setting_system_theme)
}