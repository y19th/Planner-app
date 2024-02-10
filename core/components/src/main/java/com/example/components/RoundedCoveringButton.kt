package com.example.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCoveringButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(5.dp),
    colors: ButtonColors = rememberRoundedCoveringButtonColors(),
    content: @Composable (RowScope) -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentPadding = PaddingValues(all = 0.dp),
        colors = colors,
        enabled = enabled,
        shape = shape,
        onClick = onButtonClick,
        content = content
    )
}

@Stable
@Composable
private fun rememberRoundedCoveringButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.secondary,
        disabledContentColor = MaterialTheme.colorScheme.primaryContainer
    )
}