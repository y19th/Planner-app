package com.example.ui.theme

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackBarHost = compositionLocalOf { SnackbarHostState() }