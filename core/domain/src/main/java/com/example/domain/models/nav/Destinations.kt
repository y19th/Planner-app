package com.example.domain.models.nav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.domain.R

@Stable
sealed class Destinations(@StringRes val label: Int,val icon: ImageVector,val route: Routes) {
    data object HomeDestination: Destinations(
        label = R.string.bottom_my_tasks,
        icon = Icons.Default.DateRange,
        route = Routes.HOME
    )
    data object AddDestination: Destinations(
        label = R.string.bottom_add_task,
        icon = Icons.Default.Add,
        route = Routes.ADD
    )
    data object SettingsDestination: Destinations(
        label = R.string.bottom_settings,
        icon = Icons.Default.Settings,
        route = Routes.SETTINGS
    )
}
