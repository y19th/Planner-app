package com.example.add.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.components.ColorPalette
import com.example.components.HorizontalSpacer
import com.example.components.Pin
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.ui.R
import com.example.util.extension.adaptive
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerBottomSheet(
    initialColor: Color,
    onColorChange: (Color) -> Unit,
    onDismiss: () -> Unit
) {
    val controller = rememberColorPickerController()
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    var color by remember(initialColor) {
        mutableStateOf(initialColor)
    }


    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.color_choose_title),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            /*HsvColorPicker(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.2f)
                    .padding(16.dp),
                controller = controller,
                initialColor = color,
                onColorChanged = {
                    color = it.color
                }
            )*/

            ColorPalette(
                modifier = Modifier.padding(horizontal = 16.dp),
                onColorChange = {
                    color = it
                }
            )

            VerticalSpacer(height = 12.dp)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.label_preview_pin),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                HorizontalSpacer(width = 12.dp)

                Pin(
                    pinTitle = stringResource(id = R.string.pin_example),
                    backgroundColor = color,
                    textColor = Color.adaptive(color)
                )
            }

            VerticalSpacer(height = 32.dp)

            RoundedCoveringButton(
                onButtonClick = {
                    coroutineScope.launch {
                        onColorChange.invoke(color)
                        sheetState.hide()
                    }.invokeOnCompletion { onDismiss.invoke() }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.color_ready),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            VerticalSpacer(height = 32.dp)
        }
    }
}