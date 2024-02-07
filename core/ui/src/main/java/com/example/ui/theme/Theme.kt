package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.nav.Destinations
import com.example.domain.models.nav.Routes

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
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
    outlineVariant = OutlineVariant

)

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable (PaddingValues, NavHostController) -> Unit
) {

    val navController = rememberNavController()

    val colorScheme = when {
       /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/

        darkTheme -> {
            /*TODO dark colors of success*/
            DarkColorScheme.apply {
                success = Success
                onSuccess = onSuccess
                successVariant = SuccessContainer
                onSuccessVariant = OnSuccessContainer
            }
        }
        else -> LightColorScheme
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
            containerColor = MaterialTheme.colorScheme.surface,
            bottomBar = {
                MainBottomBar(navController = navController)
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


@Composable
fun MainBottomBar(
    navController: NavController
) {
    /*TODO fix the problem with contentColor, selectedColor and background color*/
    val barColors = rememberBottomBarColors()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destinationHierarchy = navBackStackEntry?.destination?.hierarchy

    if (destinationHierarchy?.any {
        it.route == Routes.SPLASH.name
        } != true) {
        BottomNavigation(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    color = MaterialTheme.colorScheme.outline
                )
            /*TODO mind how to do a normal bottom bar*/
            /*.clip(
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )*/,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            elevation = 4.dp
        ) {
            val destinations = rememberBottomBarDestinations()

            destinations.forEach { destination ->
                val isSelected = destinationHierarchy?.any {
                    it.route == destination.route.name
                } == true

                BottomNavigationItem(
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
                            contentDescription = null,
                            tint = if (isSelected) barColors.selectedContent else barColors.content
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = destination.label),
                            style = MaterialTheme.typography.labelSmall,
                            color = if (isSelected) barColors.selectedContent else barColors.content
                        )
                    }
                )
            }
        }
    }
}

/*TODO make saveable*/
@Stable
@Composable
fun rememberBottomBarColors(): BottomBarColors {
    with(MaterialTheme.colorScheme) {
        return remember {
            BottomBarColors(
                background = surfaceVariant,
                content = onSurfaceVariant,
                selectedContent = primary
            )
        }
    }
}

@Stable
data class BottomBarColors(
    val background: Color,
    val content: Color,
    val selectedContent: Color
)

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

var successColorScheme by mutableStateOf(Success, structuralEqualityPolicy())
    private set
var onSuccessColorScheme by mutableStateOf(OnSuccess, structuralEqualityPolicy())
    private set
var successVariantColorScheme by mutableStateOf(SuccessContainer, structuralEqualityPolicy())
    private set
var onSuccessVariantColorScheme by mutableStateOf(OnSuccessContainer, structuralEqualityPolicy())
    private set

var ColorScheme.success: Color
    get() = successColorScheme
    private set(value) {
        successColorScheme = value
    }
var ColorScheme.onSuccess: Color
    get() = onSuccessColorScheme
    private set(value) {
        onSuccessColorScheme = value
    }
var ColorScheme.successVariant: Color
    get() = successVariantColorScheme
    private set(value) {
        successVariantColorScheme = value
    }

var ColorScheme.onSuccessVariant: Color
    get() = onSuccessVariantColorScheme
    private set(value) {
        onSuccessVariantColorScheme = value
    }

