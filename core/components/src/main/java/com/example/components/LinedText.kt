package com.example.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.util.AnimationDuration




@Composable
fun LinedText(
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChange: (Int,Int) -> Unit
) {
    var focused by remember {
        mutableStateOf(false)
    }
    val lineColor = run {
        val animate by animateColorAsState(
            targetValue = if(focused)  MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outline,
            animationSpec = tween(durationMillis = AnimationDuration.Fast),
            label = "recolor lined"
        )
        animate
    }

    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .wrapContentSize(align = Alignment.CenterStart)
            .widthIn(
                min = 96.dp
            )
            .pointerInput(Unit) {
                detectTapGestures { focused = focused.not() }
            }
            .drawBehind {
                drawLine(
                    color = lineColor,
                    start = Offset(
                        x = 0f,
                        y = this.size.height
                    ),
                    end = Offset(
                        x = this.size.width,
                        y = this.size.height
                    ),
                    strokeWidth = 8.toDp().value,
                    alpha = 1.0f
                )
            }
            .then(modifier)
    )
    if(focused) {
        MainTimePickerDialog(
            dialogTitle = text,
            onTimeChoose = { hour,minute ->
                onValueChange.invoke(hour,minute)
            },
            onDismiss = {
                focused = false
            }
        )
    }
}
