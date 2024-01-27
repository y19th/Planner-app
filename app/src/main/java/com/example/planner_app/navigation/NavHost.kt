package com.example.planner_app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.planner_app.navigation.models.Routes
import com.example.planner_app.presentation.screens.AddScreen
import com.example.planner_app.presentation.screens.HomeScreen
import com.example.planner_app.presentation.screens.SettingsScreen
import com.example.planner_app.presentation.screens.SplashScreen
import com.example.planner_app.presentation.viewmodels.MainViewModel

@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {

    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No viewModelStoreOwner provided"
    }


    NavHost(
        navController = navHostController,
        startDestination = Routes.SPLASH.name,
        modifier = Modifier.padding(paddingValues = paddingValues),
        builder = {
            composable(Routes.HOME.name) {
                HomeScreen(
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = MainViewModel.TAG
                    )
                )
            }
            composable(Routes.ADD.name) {
                AddScreen(navHostController)
            }
            composable(Routes.SETTINGS.name) {
                SettingsScreen()
            }
            composable(Routes.SPLASH.name) {
                SplashScreen(
                    navController = navHostController,
                    mainViewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = MainViewModel.TAG
                    )
                )
            }
        },
        enterTransition = {
            fadeIn(
                animationSpec = tween(AnimationDuration.Short, easing = LinearEasing)
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(durationMillis = AnimationDuration.Medium, easing = EaseIn),
                initialOffset = { height ->
                    height / 8
                }
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(durationMillis = AnimationDuration.Fast, easing = LinearEasing))
        }
    )
}

@Stable
object AnimationDuration {

    @Stable
    val Fast = 100

    @Stable
    val Short = 200

    @Stable
    val LessMedium = 300

    @Stable
    val Medium = 400

    @Stable
    val LessLong = 500

    @Stable
    val Long = 600
}
