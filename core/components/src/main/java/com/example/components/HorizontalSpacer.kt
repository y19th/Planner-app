package com.example.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalSpacer(
    modifier: Modifier = Modifier,
    width: Dp
) {
    Spacer(modifier = Modifier
        .width(width)
        .then(modifier)
    )
}