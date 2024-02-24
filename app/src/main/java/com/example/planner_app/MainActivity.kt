package com.example.planner_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.data.datastore.SettingsDataStore
import com.example.domain.models.droppable.Theme
import com.example.ui.theme.MainTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Suppress("PropertyName")
    @Inject
    lateinit var _dataStore: SettingsDataStore
    private val dataStore get() = _dataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            var theme by remember {
                mutableStateOf(Theme.Light as Theme)
            }

            LaunchedEffect(null) {
                dataStore.collectTheme().collect { theme = Theme.find(it) }
            }


            MainTheme(
                theme = theme
            ) { innerPadding, navController ->
                NavHostContainer(
                    navHostController = navController,
                    paddingValues = innerPadding
                )
            }
        }
    }
}

