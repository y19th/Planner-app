package com.example.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String = "",
    isError: Boolean = false,
    dropDownItemsList: List<String> = listOf(),
    onDropDownClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    var isExposed by remember {
        mutableStateOf(false)
    }

    var fieldValue by remember {
        mutableStateOf(value)
    }

    val trailingAnimation by animateFloatAsState(
        targetValue = if(isExposed) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = 400)
    )

    with(MaterialTheme.colorScheme) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExposed,
            onExpandedChange = {
                isExposed = isExposed.not()
            }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .then(modifier),
                value = fieldValue,
                onValueChange = onValueChange,
                shape = RoundedCornerShape(5.dp),
                readOnly = true,
                isError = isError,
                textStyle = MaterialTheme.typography.bodyLarge,
                label = {
                    if (labelText.isNotEmpty())
                        Text(
                            text = labelText,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.rotate(trailingAnimation)
                    )
                },
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
                    errorTextColor = error
                )
            )
            ExposedDropdownMenu(
                expanded = isExposed,
                onDismissRequest = { isExposed = false },
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .border(
                        width = 0.5.dp,
                        color = outline
                    )
            ) {
                dropDownItemsList.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ) },
                        onClick = {
                            fieldValue = item
                            onDropDownClick.invoke()
                            isExposed = false
                        },
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
            }
        }


    }
}