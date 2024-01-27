package com.example.planner_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.planner_app.navigation.models.Routes
import com.example.planner_app.presentation.screens.AddScreen
import com.example.planner_app.presentation.screens.HomeScreen
import com.example.planner_app.presentation.screens.SettingsScreen
import com.example.planner_app.presentation.screens.SplashScreen

@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.SPLASH.name,
        modifier = Modifier.padding(paddingValues = paddingValues),
        builder = {
            composable(Routes.HOME.name) {
                HomeScreen(navController = navHostController)
            }
            composable(Routes.ADD.name) {
                AddScreen(navHostController)
            }
            composable(Routes.SETTINGS.name) {
                SettingsScreen()
            }
            composable(Routes.SPLASH.name) {
                SplashScreen(navHostController)
            }
        }
    )
}