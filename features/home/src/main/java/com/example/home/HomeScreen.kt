package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.events.MainEvent
import com.example.domain.models.TaskColors
import com.example.domain.models.TaskStatus
import com.example.home.components.TaskItem
import com.example.ui.R
import com.example.ui.theme.onSuccess
import com.example.ui.theme.onSuccessVariant
import com.example.ui.theme.success
import com.example.ui.theme.successVariant

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: com.example.home.viewmodels.MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
           modifier = modifier
               .fillMaxWidth()
               .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 8.dp)
        ){
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(start = 12.dp, end = 12.dp, top = 16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = stringResource(id = R.string.tasks_for_today,state.taskList.size),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                /*TODO add vector picture*/
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.taskList.size){
                    TaskItem(
                        model = state.taskList[it],
                        onDoneClick = {
                            viewModel.onEvent(MainEvent.OnTaskDone(taskId = state.taskList[it].id))
                        }
                    )
                }
            }
        }
    }
}

/*TODO make taskColors saveable*/
@Composable
fun rememberColorGroup(status: TaskStatus): TaskColors {
    val scheme = MaterialTheme.colorScheme
    return remember(key1 = status) {
        when (status) {
            TaskStatus.IN_PROGRESS -> {
                TaskColors(
                    container = scheme.primaryContainer,
                    onContainer = scheme.onPrimaryContainer,
                    pin = scheme.secondary,
                    onPin = scheme.onSecondary,
                    done = scheme.primary,
                    onDone = scheme.onPrimary,
                    cancel = scheme.tertiary,
                    onCancel = scheme.onTertiary
                )
            }

            TaskStatus.COMPLETED -> {
                TaskColors(
                    container = scheme.successVariant,
                    onContainer = scheme.onSuccessVariant,
                    pin = scheme.success,
                    onPin = scheme.onSuccess,
                    done = null,
                    onDone = null,
                    cancel = null,
                    onCancel = null
                )
            }

            TaskStatus.CANCELLED -> {
                TaskColors(
                    container = scheme.errorContainer,
                    onContainer = scheme.onErrorContainer,
                    pin = scheme.error,
                    onPin = scheme.onError,
                    done = null,
                    onDone = null,
                    cancel = null,
                    onCancel = null
                )
            }
        }
    }
}

