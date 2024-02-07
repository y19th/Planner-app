package com.example.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(
    modifier: Modifier = Modifier,
    height: Dp
) {
    Spacer(modifier = Modifier
        .height(height)
        .then(modifier)
    )
}

