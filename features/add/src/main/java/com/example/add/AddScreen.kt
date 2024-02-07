package com.example.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add.components.ScreenColumn
import com.example.add.components.ScreenRow
import com.example.add.viewmodels.AddViewModel
import com.example.components.AddButton
import com.example.components.LinedDatePicker
import com.example.components.RoundedCoveringButton
import com.example.components.RoundedTextField
import com.example.ui.R

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
                .padding(vertical = 8.dp, horizontal = 20.dp)
        ) {
            ScreenColumn(
                title = stringResource(id = R.string.task_title_text),
            ) {
                RoundedTextField(
                    value = state.value.taskTitle,
                    onValueChange = { newValue ->
                        viewModel.onEvent(com.example.domain.events.AddEvents.OnChangeTaskTitle(newValue))
                    },
                    placeholder = stringResource(id = R.string.task_title_placeholder)
                )
            }

            ScreenRow {
                Text(
                    text = stringResource(id = R.string.task_pins_text),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                AddButton {
                    /*TODO*/
                }
            }

            ScreenColumn(
                title = stringResource(id = R.string.task_date_text)
            ) {
                LinedDatePicker(
                    modifier = Modifier.padding(vertical = 6.dp),
                    title = stringResource(id = R.string.task_date_from),
                    value = state.value.taskTitle
                )
                LinedDatePicker(
                    modifier = Modifier.padding(vertical = 6.dp),
                    title = stringResource(id = R.string.task_date_to),
                    value = state.value.taskTitle
                )
            }

            ScreenRow {
                Text(
                    text = stringResource(id = R.string.task_emoji_text),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                AddButton {
                    /*TODO*/
                }
            }

            ScreenColumn(
                title = stringResource(id = R.string.task_describe_text),
                withDivider = false
            ) {
                RoundedTextField(
                    modifier = Modifier
                        .heightIn(
                            min = 128.dp
                        ),
                    value = state.value.taskTitle,
                    placeholder = stringResource(id = R.string.task_describe_placeholder),
                    onValueChange = {
                        viewModel.onEvent(com.example.domain.events.AddEvents.OnChangeTaskTitle(it))
                    },
                    shape = RoundedCornerShape(16.dp),
                    singleLine = false
                )
            }

            RoundedCoveringButton(
                onButtonClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.task_add),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
