package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = CircleShape,
    placeholder: String = "",
    singleLine: Boolean = true,
    isError: Boolean = false
) {
    with(MaterialTheme.colorScheme) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = singleLine,
                isError = isError,
                shape = shape,
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = outline,
                    focusedBorderColor = primary,
                    errorBorderColor = error,
                    unfocusedLabelColor = onSurfaceVariant,
                    focusedLabelColor = primary,
                    errorLabelColor = error,
                    unfocusedTrailingIconColor = onSurfaceVariant,
                    focusedTrailingIconColor = primary,
                    errorTrailingIconColor = error,
                    unfocusedTextColor = onSurfaceVariant,
                    focusedTextColor = onSurfaceVariant,
                    errorTextColor = error,
                    unfocusedContainerColor = primaryContainer,
                    focusedContainerColor = primaryContainer,
                    unfocusedPlaceholderColor = outline,
                    errorContainerColor = errorContainer
                ),
                container = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = primaryContainer,
                                shape = shape
                            )
                            .border(
                                width = 0.25.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = shape
                            )
                            .clip(shape)
                    )
                }
            )
        }

        /*OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 0.dp)
                .then(modifier),
            value = value,
            onValueChange = onValueChange,
            shape = CircleShape,
            singleLine = true,
            isError = isError,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = MaterialTheme.typography.labelMedium,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = outline,
                focusedBorderColor = primary,
                errorBorderColor = error,
                unfocusedLabelColor = onSurfaceVariant,
                focusedLabelColor = primary,
                errorLabelColor = error,
                unfocusedTrailingIconColor = onSurfaceVariant,
                focusedTrailingIconColor = primary,
                errorTrailingIconColor = error,
                unfocusedTextColor = onSurfaceVariant,
                focusedTextColor = onSurfaceVariant,
                errorTextColor = error,
                unfocusedContainerColor = primaryContainer,
                focusedContainerColor = primaryContainer,
                unfocusedPlaceholderColor = outline,
                errorContainerColor = errorContainer
            )
        )*/
    }
}