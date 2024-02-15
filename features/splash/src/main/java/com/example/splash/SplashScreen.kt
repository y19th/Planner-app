package com.example.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.events.MainEvent
import com.example.domain.models.nav.Routes
import com.example.home.viewmodels.MainViewModel
import com.example.ui.R

@Composable
fun SplashScreen(
    navController: NavController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val isLoading = mainViewModel.state.collectAsState().value.isLoading

    LaunchedEffect(null) {
        mainViewModel.onEvent(MainEvent.OnRefresh)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_title),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        LaunchedEffect(isLoading) {
            if(!isLoading)navController.navigate(route = Routes.HOME.name)
        }
    }
}