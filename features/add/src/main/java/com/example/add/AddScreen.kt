package com.example.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add.viewmodels.AddViewModel
import com.example.components.LinedDatePicker
import com.example.components.MainDivider
import com.example.components.VerticalSpacer
import com.example.domain.events.AddEvents
import com.example.ui.R

@Composable
fun AddScreen(
    navController: NavController,
    viewModel: AddViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val isTaskTyped by rememberSaveable(state.taskDate) {
        mutableStateOf(state.taskDate.isNotEmpty())
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.add_tasks),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        MainDivider()

        LabelledTextField(
            value = "",
            label = stringResource(id = R.string.label_new_task),
            onValueChange = {

            }
        )

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(id = R.string.label_add_pins),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        
        LabelledTextField(
            value = state.taskDate,
            label = stringResource(id = R.string.label_task_date),
            onValueChange = {
                viewModel.onEvent(AddEvents.OnDateChange(newDate = it))
            } 
        )

        AnimatedVisibility(
            visible = isTaskTyped
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
            value = state.taskDescription,
            label = stringResource(id = R.string.label_desc),
            onValueChange = {
                viewModel.onEvent(AddEvents.OnDescriptionChange(newDesc = it))
            }
        )
    }
}







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelledTextField(
    modifier: Modifier = Modifier,
    label: String = "label",
    value: String,
    isEnabled: Boolean = true,
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
            enabled = isEnabled
        ) { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = isEnabled,
                isError = isError,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
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
                        contentDescription = null ,
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
