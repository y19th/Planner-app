package com.example.components

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainDivider(
    modifier: Modifier = Modifier,
    height: Dp = 0.25.dp,
    color: Color = MaterialTheme.colorScheme.outlineVariant
) {
    Divider(
        modifier = modifier,
        thickness = height,
        color = color
    )
}