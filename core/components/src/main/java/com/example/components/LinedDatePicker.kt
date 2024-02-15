package com.example.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LinedDatePicker(
    modifier: Modifier = Modifier,
    title: String = "from",
    value: String = "",
    onValueChange: (Int,Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(0.3f)
        )

        Spacer(modifier = Modifier.weight(0.05f))

        LinedText(
            modifier = Modifier.weight(0.65f),
            text = value,
            onValueChange = onValueChange
        )
    }
}