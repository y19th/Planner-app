package com.example.planner_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.planner_app.navigation.NavHostContainer
import com.example.planner_app.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme { innerPadding, navController ->
                NavHostContainer(
                    navHostController = navController,
                    paddingValues = innerPadding
                )
            }
        }
    }
}

