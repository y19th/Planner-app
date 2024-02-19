package com.example.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.components.MainDivider
import com.example.components.Pin
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.domain.events.MainEvent
import com.example.domain.models.TaskModel
import com.example.domain.models.TaskStatus
import com.example.ui.R
import com.example.util.extension.vector

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    model: TaskModel = TaskModel(),
    deadLine: String = "32 минуты",
    onEvent: (MainEvent) -> Unit,
    onChangeTaskEvent: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(0.75f)
            )

            Spacer(modifier = Modifier.weight(0.25f))

            TaskIconDropDown(
                iconRes = R.drawable.ic_task_dots,
                modelId = model.id,
                onEvent = onEvent,
                onChangeTaskEvent = onChangeTaskEvent
            )
        }

        VerticalSpacer(height = 4.dp)

        Row (horizontalArrangement = Arrangement.spacedBy(4.dp)){
            Text(
                text = stringResource(id = R.string.status_task),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = stringResource(id = model.status.value()),
                style = MaterialTheme.typography.labelMedium,
                color = model.status.color()
                )

        }

        Row (horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(id = R.string.deadline),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = deadLine,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )

        }

        VerticalSpacer(height = 4.dp)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(model.taskPin.size) { index ->
                with(model.taskPin[index]) {
                    Pin(
                        title = name,
                        backgroundColor = containerColor,
                        textColor = textColor
                    )
                }
            }
        }

        VerticalSpacer(height = 12.dp)

        Text(
            text = model.content,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        VerticalSpacer(height = 12.dp)
        
        AnimatedVisibility(model.status == TaskStatus.IN_PROGRESS) {
            RoundedCoveringButton(
                onButtonClick = {
                    onEvent.invoke(MainEvent.OnTaskDone(taskId = model.id))
                }
            ) {
                Text(
                    text = stringResource(id = R.string.done),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        VerticalSpacer(height = 26.dp)

        MainDivider()
    }
}