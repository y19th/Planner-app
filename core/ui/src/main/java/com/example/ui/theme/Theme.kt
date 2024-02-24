package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.droppable.Theme
import com.example.domain.models.nav.Destinations
import com.example.domain.models.nav.Routes
import com.example.ui.theme.local.LocalSnackBarHost
import com.example.util.extension.contains
import com.example.util.extension.onSuccess
import com.example.util.extension.onSuccessVariant
import com.example.util.extension.success
import com.example.util.extension.successVariant

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(

    background = Background,

    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainer,
    onSurfaceVariant = OnSurfaceContainer,

    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = Secondary,
    onSecondary = OnSecondary,

    tertiary = Tertiary,
    onTertiary = OnTertiary,

    outline = Outline,
    outlineVariant = OutlineVariant,

    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer

)

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    theme: Theme = Theme.Light,
    dynamicColor: Boolean = false,
    content: @Composable (PaddingValues, NavHostController) -> Unit
) {

    val navController = rememberNavController()
/*
    val colorScheme = when {
       *//* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*//*

        darkTheme -> {
            *//*TODO dark colors of success*//*
            DarkColorScheme.apply {
                success = Success
                onSuccess = OnSuccess
                successVariant = SuccessContainer
                onSuccessVariant = OnSuccessContainer
            }
        }
        else -> LightColorScheme.apply {
            success = Success
            onSuccess = OnSuccess
            successVariant = SuccessContainer
            onSuccessVariant = OnSuccessContainer
        }
    }*/
    val colorScheme = when(theme) {
        is Theme.Light -> {
            LightColorScheme.apply {
                success = Success
                onSuccess = OnSuccess
                successVariant = SuccessContainer
                onSuccessVariant = OnSuccessContainer
            }
        }
        is Theme.Dark -> {
            DarkColorScheme.apply {
                success = Success
                onSuccess = OnSuccess
                successVariant = SuccessContainer
                onSuccessVariant = OnSuccessContainer
            }
        }
        is Theme.System -> {
            DarkColorScheme.apply {
                success = Success
                onSuccess = OnSuccess
                successVariant = SuccessContainer
                onSuccessVariant = OnSuccessContainer
            }
        }
    }


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                MainBottomBar(navController = navController)
            },
            snackbarHost = {
                SnackbarHost(hostState = LocalSnackBarHost.current)
            },
            content = { padding ->
                content(padding,navController)
            }
        )
    }
}

@Composable
fun BottomBarTheme(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = { MainBottomBar(navController = navController) },
        content = { paddingValues ->
            content.invoke(paddingValues)
        }
    )
}

/* TODO make bottom bar background color not transparent*/
@Composable
fun MainBottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val navDestination = navBackStackEntry?.destination

    if(navDestination?.contains(Routes.SPLASH.name) != true) {
        NavigationBar(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                ),
            containerColor = Color.Transparent
        ) {
            val destinations = rememberBottomBarDestinations()

            destinations.forEach { destination ->
                val isSelected = navDestination?.contains(destination.route.name) == true

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(destination.route.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destination.label),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        }
    }
}

@Stable
@Composable
fun rememberBottomBarDestinations(): List<Destinations> {
    return rememberSaveable {
        listOf(
            Destinations.HomeDestination,
            Destinations.AddDestination,
            Destinations.SettingsDestination
        )
    }
}

