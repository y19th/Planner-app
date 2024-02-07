package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Pin(
    modifier: Modifier = Modifier,
    pinTitle: String = "pin",
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    textColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .then(modifier)
    ) {
        Text(
            text = pinTitle,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}