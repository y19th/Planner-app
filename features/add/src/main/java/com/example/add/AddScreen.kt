package com.example.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.add.viewmodels.AddViewModel
import com.example.components.LinedDatePicker
import com.example.components.MainDivider
import com.example.components.Pin
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.domain.events.AddEvents
import com.example.domain.models.Droppable
import com.example.domain.models.TaskPin
import com.example.ui.R
import com.example.ui.theme.MainTheme

//@Preview(showBackground = true)
//@PreviewScreenSizes
@Composable
fun PreviewAddScreen() {
    MainTheme { innerPadding, navController ->
        AddScreen(navController = rememberNavController())
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddScreen(
    navController: NavController,
    viewModel: AddViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.add_tasks),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        MainDivider()

        LabelledTextField(
            value = state.taskTitle,
            label = stringResource(id = R.string.label_new_task),
            onValueChange = {
                viewModel.onEvent(AddEvents.OnTaskTitleChange(newValue = it))
            }
        )

        Row (
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Start)
                .clickable {
                    viewModel.onEvent(
                        AddEvents.OnNavigateToPin(
                            pinId = TaskPin.DefaultId,
                            navController = navController
                        )
                    )
                }
                .clip(CircleShape),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(id = R.string.label_add_pins),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        if(state.taskPins.isNotEmpty()) {
            Column {
                Text(
                    text = stringResource(id = R.string.pin_now),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                VerticalSpacer(height = 4.dp)

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(state.taskPins.size) {
                        Pin(
                            modifier = Modifier
                                .clickable {
                                   viewModel.onEvent(AddEvents.OnNavigateToPin(
                                       pinId = state.taskPins[it].id,
                                       navController = navController
                                   ))
                                },
                            pinTitle = state.taskPins[it].name,
                            backgroundColor = state.taskPins[it].containerColor
                        )
                    }
                }
            }
        }
        
        LabelledTextField(
            value = state.taskDate,
            label = stringResource(id = R.string.label_task_date),
            onValueChange = {
                viewModel.onEvent(AddEvents.OnDateChange(newDate = it))
            } 
        )

        AnimatedVisibility(
            visible = state.taskDate.isNotEmpty()
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                LinedDatePicker(
                    title = stringResource(id = R.string.label_time_from),
                    value = state.taskTimeFrom
                )
                LinedDatePicker(
                    title = stringResource(id = R.string.label_time_to),
                    value = state.taskTimeTo
                )
            }
        }


        LabelledTextField(
            modifier = Modifier.heightIn(min = 96.dp),
            value = state.taskDescription,
            label = stringResource(id = R.string.label_desc),
            singleLine = false,
            onValueChange = {
                viewModel.onEvent(AddEvents.OnDescriptionChange(newDesc = it))
            }
        )

        RoundedCoveringButton(
            onButtonClick = { /*TODO*/ },
            enabled = state.isValid,
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add_task),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}





/*TODO fix colors*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledTextField(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String,
    isEnabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(5.dp),
    onValueChange: (String) -> Unit,
) {
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
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            enabled = isEnabled,
            readOnly = false
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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Blue,
                    focusedBorderColor = Color.Blue
                ),
                container = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = shape
                            )
                            .clip(shape)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.clickable {
                            onValueChange.invoke("")
                        }
                    )
                }
            )
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

    val trailingAnimation by animateFloatAsState(
        targetValue = if(isExposed) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = 400)
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant
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
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Blue,
                        focusedBorderColor = Color.Blue
                    ),
                    container = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    shape = shape
                                )
                                .clip(shape)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
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
