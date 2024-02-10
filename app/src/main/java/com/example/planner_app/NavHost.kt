package com.example.planner_app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.add.AddScreen
import com.example.add.PinAddScreen
import com.example.add.viewmodels.AddViewModel
import com.example.domain.models.nav.Routes
import com.example.home.HomeScreen
import com.example.home.viewmodels.MainViewModel
import com.example.settings.SettingsScreen
import com.example.splash.SplashScreen
import com.example.util.Animations

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
            composable(
                route = Routes.HOME.name,
                enterTransition = { Animations.slideInWithFadeFromBottom },
                exitTransition = { Animations.fastFadeOut },
                popEnterTransition = { Animations.mediumFadeIn }
            ) {
                HomeScreen(
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = MainViewModel.TAG
                    )
                )
            }
            composable(
                route = Routes.ADD.name,
                enterTransition = { Animations.slideInWithFadeFromBottom },
                exitTransition = { Animations.fastFadeOut },
                popEnterTransition = { Animations.mediumFadeIn }
            ) {
                AddScreen(
                    navController = navHostController,
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = AddViewModel.TAG
                    )
                )
            }
            composable(
                route = Routes.ADD.routeWith("{pinId}"),
                arguments = listOf(navArgument("pinId") { type = NavType.StringType}),
                enterTransition = { Animations.slideInWithFadeFromRight },
                exitTransition = { Animations.slideOutWithFadeToRight }
            ) {
                PinAddScreen(
                    navController = navHostController,
                    viewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = AddViewModel.TAG
                    ),
                    pinId = it.arguments?.getString("pinId") ?: ""
                )
            }
            composable(route = Routes.SETTINGS.name,
                ) {
                SettingsScreen()
            }
            composable(
                route = Routes.SPLASH.name,
                enterTransition = { Animations.shortFadeIn },
                exitTransition = { Animations.fastFadeOut }
            ) {
                SplashScreen(
                    navController = navHostController,
                    mainViewModel = hiltViewModel(
                        viewModelStoreOwner = viewModelStoreOwner,
                        key = MainViewModel.TAG
                    )
                )
            }
        }
    )
}

