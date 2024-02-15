package com.example.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.util.AnimationDuration
import com.example.util.Droppable
import com.example.util.extension.or

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledTextField(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String,
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(5.dp),
    onValueChange: (String) -> Unit,
) {

    val focusRequester = remember { FocusRequester() }

    var isFocused by remember {
        mutableStateOf(false)
    }

    val colors = LabelledTextFieldDefaults.Default.colors()

    val borderColor by animateColorAsState(
        targetValue = colors.unfocusableBorderColor.or(isFocused,colors.focusableBorderColor),
        label = "borderColor",
        animationSpec = tween(durationMillis = AnimationDuration.LessMedium)
    )
    val trailingColor by animateColorAsState(
        targetValue = colors.unfocusableTrailingColor.or(isFocused, colors.focusableTrailingColor),
        label = "trailingColor",
        animationSpec = tween(durationMillis = AnimationDuration.LessMedium)
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpacer(height = 6.dp)

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = isFocused.not() }
                .then(modifier)
            ,
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodySmall.copy(
                color = colors.focusableTextColor
            ),
            enabled = isEnabled,
            readOnly = readOnly
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = isEnabled,
                isError = isError,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 6.dp
                ),
                container = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = shape
                            )
                            .border(
                                color = borderColor,
                                shape = shape,
                                width = 1.dp
                            )
                            .clip(shape)
                    )
                },
                trailingIcon = {
                    if(value.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            tint = trailingColor,
                            modifier = Modifier.clickable {
                                onValueChange.invoke("")
                            }
                        )
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledDatePicker(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String,
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(5.dp),
    onValueChange: (String) -> Unit = {},
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    var isExposed by rememberSaveable {
        mutableStateOf(false)
    }

    val clickable = Modifier.clickable(
        interactionSource = interactionSource,
        indication = LocalIndication.current
    ) {
        isExposed = isExposed.not()
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    val colors = LabelledTextFieldDefaults.DropDown.colors()

    val trailingAnimation by animateFloatAsState(
        targetValue = if(isExposed) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = AnimationDuration.Medium)
    )

    val borderColor by animateColorAsState(
        targetValue = colors.focusableBorderColor.or(isExposed,colors.unfocusableBorderColor),
        label = "borderColor",
        animationSpec = tween(durationMillis = AnimationDuration.LessMedium)
    )
    val trailingColor by animateColorAsState(
        targetValue = colors.focusableTrailingColor.or(isExposed, colors.unfocusableTrailingColor),
        label = "trailingColor",
        animationSpec = tween(durationMillis = AnimationDuration.LessMedium)
    )



    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpacer(height = 6.dp)

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodySmall.copy(
                color = colors.focusableTextColor
            ),
            enabled = isEnabled,
            readOnly = true
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = isEnabled,
                isError = isError,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 6.dp
                ),
                container = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = shape
                            )
                            .border(
                                color = borderColor,
                                shape = shape,
                                width = 1.dp
                            )
                            .clip(shape)
                            .then(clickable)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = trailingColor,
                        modifier = Modifier.rotate(trailingAnimation)
                    )
                }
            )
        }
        if (isExposed) {
            DatePickerDialog(
                onDismissRequest = { isExposed = false },
                confirmButton = {
                   TextButton(
                       onClick = {
                           onValueChange.invoke(
                               datePickerState.selectedDateMillis.toString()
                           )
                           isExposed = false
                       }
                   ) {
                       Text(
                           text = stringResource(id = R.string.labelled_date_picker_ready),
                           style = MaterialTheme.typography.labelMedium
                       )
                   }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    title = {
                        Text(
                            text = stringResource(id = R.string.labelled_date_picker_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 16.dp)
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Droppable> LabelledTextDropDown(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String,
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(5.dp),
    dropDownItems: List<T> = listOf(),
    onDropDownClick: (T) -> Unit,
    onValueChange: (String) -> Unit = {},
) {
    var isExposed by rememberSaveable {
        mutableStateOf(false)
    }
    val colors = LabelledTextFieldDefaults.DropDown.colors()

    val trailingAnimation by animateFloatAsState(
        targetValue = if(isExposed) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = AnimationDuration.Medium)
    )

    val borderColor by animateColorAsState(
        targetValue = colors.focusableBorderColor.or(isExposed,colors.unfocusableBorderColor),
        label = "borderColor",
        animationSpec = tween(durationMillis = AnimationDuration.LessMedium)
    )
    val trailingColor by animateColorAsState(
        targetValue = colors.focusableTrailingColor.or(isExposed, colors.unfocusableTrailingColor),
        label = "trailingColor",
        animationSpec = tween(durationMillis = AnimationDuration.LessMedium)
    )



    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        VerticalSpacer(height = 6.dp)


        ExposedDropdownMenuBox(
            expanded = isExposed,
            onExpandedChange = {
                isExposed = isExposed.not()
            }
        ) {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .menuAnchor()
                    .then(modifier),
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    color = colors.focusableTextColor
                ),
                enabled = isEnabled,
                readOnly = true
            ) { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = isEnabled,
                    isError = isError,
                    singleLine = singleLine,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 6.dp
                    ),
                    container = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = shape
                                )
                                .border(
                                    color = borderColor,
                                    shape = shape,
                                    width = 1.dp
                                )
                                .clip(shape)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = trailingColor,
                            modifier = Modifier.rotate(trailingAnimation)
                        )
                    }
                )
            }
            ExposedDropdownMenu(
                expanded = isExposed,
                onDismissRequest = { isExposed = isExposed.not() }
            ) {
                dropDownItems.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(id = item.stringId()),
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        onClick = {
                            onDropDownClick.invoke(item)
                            isExposed = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }
    }
}



sealed interface LabelledTextFieldDefaults {

    data object Default : LabelledTextFieldDefaults {

        @Composable
        override fun colors(): LabelledColors {
            return LabelledColors(
                focusableBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusableBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                errorBorderColor = MaterialTheme.colorScheme.error,
                focusableTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusableTextColor = MaterialTheme.colorScheme.outline,
                errorTextColor = MaterialTheme.colorScheme.onError,
                focusableTrailingColor = MaterialTheme.colorScheme.tertiary,
                unfocusableTrailingColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorTrailingColor = MaterialTheme.colorScheme.error
            )
        }
    }
    data object DropDown: LabelledTextFieldDefaults {

        @Composable
        override fun colors(): LabelledColors {
            return LabelledColors(
                focusableBorderColor = MaterialTheme.colorScheme.tertiary,
                unfocusableBorderColor = MaterialTheme.colorScheme.surfaceVariant,
                errorBorderColor = MaterialTheme.colorScheme.error,
                focusableTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusableTextColor = MaterialTheme.colorScheme.outline,
                errorTextColor = MaterialTheme.colorScheme.onError,
                focusableTrailingColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusableTrailingColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorTrailingColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }

    @Composable
    fun colors(): LabelledColors

}

@Stable
data class LabelledColors(
    val focusableBorderColor: Color,
    val unfocusableBorderColor: Color,
    val errorBorderColor: Color,
    val focusableTrailingColor: Color,
    val unfocusableTrailingColor: Color,
    val errorTrailingColor: Color,
    val focusableTextColor: Color,
    val unfocusableTextColor: Color,
    val errorTextColor: Color
)
