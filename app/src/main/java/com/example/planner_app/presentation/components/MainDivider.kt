package com.example.planner_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainDivider(
    modifier: Modifier = Modifier,
    height: Dp = 0.25.dp,
    color: Color = MaterialTheme.colorScheme.outlineVariant
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                color = color,
                shape = RectangleShape
            )
            .then(modifier)
    )
}