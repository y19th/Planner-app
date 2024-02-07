package com.example.planner_app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.domain.models.nav.Routes
import com.example.add.AddScreen
import com.example.home.HomeScreen
import com.example.settings.SettingsScreen
import com.example.splash.SplashScreen
import com.example.home.viewmodels.MainViewModel
import com.example.util.AnimationDuration

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

