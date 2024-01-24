package com.example.planner_app.navigation.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.planner_app.R

sealed class Destinations(@StringRes val label: Int,val icon: ImageVector,val route: Routes) {
    data object HomeDestination: Destinations(
        label = R.string.home_destination,
        icon = Icons.Default.DateRange,
        route = Routes.HOME
    )
    data object AddDestination: Destinations(
        label = R.string.add_destination,
        icon = Icons.Default.Add,
        route = Routes.ADD
    )
    data object SettingsDestination: Destinations(
        label = R.string.setting_destination,
        icon = Icons.Default.Settings,
        route = Routes.SETTINGS
    )
}
