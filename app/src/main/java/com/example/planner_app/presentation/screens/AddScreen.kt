package com.example.planner_app.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.planner_app.R
import com.example.planner_app.domain.events.AddEvents
import com.example.planner_app.navigation.AnimationDuration
import com.example.planner_app.presentation.components.HorizontalSpacer
import com.example.planner_app.presentation.components.MainDivider
import com.example.planner_app.presentation.components.VerticalSpacer
import com.example.planner_app.presentation.viewmodels.AddViewModel

@Composable
fun AddScreen(
    navController: NavController,
    viewModel: AddViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(id = R.string.add_task_destinations),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(vertical = 24.dp, horizontal = 20.dp)
        ) {
            ScreenColumn(
                title = stringResource(id = R.string.task_title_text),
            ) {
                RoundedTextField(
                    value = state.value.taskTitle,
                    onValueChange = { newValue ->
                        viewModel.onEvent(AddEvents.OnChangeTaskTitle(newValue))
                    },
                    placeholder = stringResource(id = R.string.task_title_placeholder)
                )

                VerticalSpacer(height = 16.dp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.task_pins_text),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                AddButton {
                    /*TODO*/
                }
            }

            MainDivider()

            ScreenColumn(
                modifier = Modifier.padding(vertical = 16.dp),
                title = stringResource(id = R.string.task_date_text)
            ) {
                LinedDatePicker(modifier = Modifier.padding(vertical = 6.dp))
            }
        }
    }
}

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(2.dp)
            )
            .clip(RoundedCornerShape(2.dp))
            .clickable { onClick.invoke() }
            .then(modifier)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = null,
            modifier = Modifier.padding(all = 4.dp)
        )
    }
}

@Composable
private fun ScreenColumn(
    modifier: Modifier = Modifier,
    title: String = "",
    withDivider: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        VerticalSpacer(height = 6.dp)

        content.invoke(this)

        if(withDivider)MainDivider()
    }
}



@Composable
fun LinedDatePicker(
    modifier: Modifier = Modifier,
    title: String = "from"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalSpacer(width = 24.dp)

        LinedText(
            text = "asd"
        )
    }
}


@Composable
fun LinedText(
    text: String = ""
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
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier
            .widthIn(
                min = 64.dp
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
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
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
                singleLine = true,
                isError = isError,
                shape = CircleShape,
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
                                shape = CircleShape
                            )
                            .border(
                                width = 0.25.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = CircleShape
                            )
                            .clip(CircleShape)
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