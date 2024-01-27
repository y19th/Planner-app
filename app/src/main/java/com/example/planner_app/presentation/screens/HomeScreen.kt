package com.example.planner_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.planner_app.R
import com.example.planner_app.domain.models.TaskColors
import com.example.planner_app.domain.models.TaskModel
import com.example.planner_app.domain.models.TaskPin
import com.example.planner_app.domain.models.TaskStatus
import com.example.planner_app.presentation.components.Divider
import com.example.planner_app.presentation.components.HorizontalSpacer
import com.example.planner_app.presentation.components.Pin
import com.example.planner_app.presentation.components.VerticalSpacer
import com.example.planner_app.presentation.viewmodels.MainViewModel
import com.example.planner_app.ui.theme.onSuccess
import com.example.planner_app.ui.theme.onSuccessVariant
import com.example.planner_app.ui.theme.success
import com.example.planner_app.ui.theme.successVariant

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    val model = TaskModel(
        title = state.isLoading.toString(),
        content = "wash the dishes and cook the dinner. after that pet the cat and feed oleg",
        status = TaskStatus.IN_PROGRESS,
        taskPin = listOf(TaskPin(name = "important"), TaskPin(name = "home based")),
    )

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
                    text = stringResource(id = R.string.tasks_for_today,2 /*TODO*/),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                /*TODO add vector picture*/
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(5){
                    TaskItem(
                        model = model,
                        onDoneClick = {
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    model: TaskModel = TaskModel(),
    taskDeadLine: String = "32 mins",
    onDoneClick: () -> Unit,
    status: TaskStatus = TaskStatus.IN_PROGRESS
) {
    var remStatus by remember {
        mutableStateOf(status)
    }
    val colorGroup = rememberColorGroup(status = remStatus)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorGroup.container,
                shape = RoundedCornerShape(16.dp)
            )
            /*.border(
                width = 0.25.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )*/
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(bottom = 10.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_smile_happy),
                tint = colorGroup.onContainer,
                contentDescription = null
            )

            HorizontalSpacer(width = 8.dp)

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorGroup.onContainer
                )
                if(status == TaskStatus.IN_PROGRESS) {
                    Text(
                        text = stringResource(id = R.string.deadline_pattern, taskDeadLine),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }


        }

        Divider()

        VerticalSpacer(height = 10.dp)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(model.taskPin.size) { index ->
                Pin(
                    pinTitle = model.taskPin[index].name,
                    backgroundColor = colorGroup.pin,
                    textColor = colorGroup.onPin
                )
            }
        }

        VerticalSpacer(height = 10.dp)

        Text(
            text = model.content,
            style = MaterialTheme.typography.bodyMedium,
            color = colorGroup.onContainer
        )

        if(status == TaskStatus.IN_PROGRESS) {

            VerticalSpacer(height = 32.dp)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(0.47f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorGroup.done ?: MaterialTheme.colorScheme.primary
                    ),
                    onClick = {
                        remStatus = TaskStatus.COMPLETED
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.button_done),
                        style = MaterialTheme.typography.labelLarge,
                        color = colorGroup.onDone ?: MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.weight(0.06f))

                Button(
                    modifier = Modifier.weight(0.47f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorGroup.cancel ?: MaterialTheme.colorScheme.tertiary
                    ),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = stringResource(id = R.string.button_cancel),
                        style = MaterialTheme.typography.labelLarge,
                        color = colorGroup.onCancel ?: MaterialTheme.colorScheme.onTertiary
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

