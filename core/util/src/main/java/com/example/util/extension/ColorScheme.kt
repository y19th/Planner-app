package com.example.util.extension

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

var successColorScheme by mutableStateOf(Color.Green, structuralEqualityPolicy())
    private set
var onSuccessColorScheme by mutableStateOf(Color.White, structuralEqualityPolicy())
    private set
var successVariantColorScheme by mutableStateOf(Color.Cyan, structuralEqualityPolicy())
    private set
var onSuccessVariantColorScheme by mutableStateOf(Color.White, structuralEqualityPolicy())
    private set


@Suppress("UnusedReceiverParameter")
var ColorScheme.success: Color
    get() = successColorScheme
    set(value) {
        successColorScheme = value
    }
@Suppress("UnusedReceiverParameter")
var ColorScheme.onSuccess: Color
    get() = onSuccessColorScheme
    set(value) {
        onSuccessColorScheme = value
    }
@Suppress("UnusedReceiverParameter")
var ColorScheme.successVariant: Color
    get() = successVariantColorScheme
    set(value) {
        successVariantColorScheme = value
    }
@Suppress("UnusedReceiverParameter")
var ColorScheme.onSuccessVariant: Color
    get() = onSuccessVariantColorScheme
    set(value) {
        onSuccessVariantColorScheme = value
    }
