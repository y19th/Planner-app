package com.example.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add.viewmodels.AddViewModel
import com.example.components.LabelledDatePicker
import com.example.components.LabelledTextField
import com.example.components.LinedTimePicker
import com.example.components.MainDivider
import com.example.components.Pin
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.domain.events.AddEvents
import com.example.domain.models.TaskPin
import com.example.ui.R
import com.example.ui.theme.LocalSnackBarHost

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
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    viewModel.onEvent(
                        AddEvents.OnNavigateToPin(
                            pinId = TaskPin.DefaultId,
                            navController = navController
                        )
                    )
                },
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
                            title = state.taskPins[it].name,
                            backgroundColor = state.taskPins[it].containerColor,
                            textColor = state.taskPins[it].textColor
                        )
                    }
                }
            }
        }
        
        LabelledDatePicker(
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
                LinedTimePicker(
                    title = stringResource(id = R.string.label_time_from),
                    value = state.taskTimeFrom?.toString() ?: "",
                    onValueChange = { hour,minute ->
                        viewModel.onEvent(AddEvents.OnTimeFromChange(
                            newHour = hour,
                            newMinute = minute
                        ))
                    }
                )
                LinedTimePicker(
                    title = stringResource(id = R.string.label_time_to),
                    value = state.taskTimeTo?.toString() ?: "",
                    onValueChange = { hour,minute ->
                        viewModel.onEvent(AddEvents.OnTimeToChange(
                            newHour = hour,
                            newMinute = minute
                        ))
                    }
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
            onButtonClick = {
                viewModel.onEvent(AddEvents.OnTaskAdd(navController))
            },
            enabled = state.isValid
        ) {
            Text(
                text = stringResource(id = R.string.add_task),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}









