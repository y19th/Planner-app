package com.example.planner_app

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.planner_app.ui.theme.OnSuccess
import com.example.planner_app.ui.theme.OnSuccessContainer
import com.example.planner_app.ui.theme.Success
import com.example.planner_app.ui.theme.SuccessContainer

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
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
                .padding(vertical = 16.dp, horizontal = 12.dp)
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



            TaskItem(
                taskTitle = "Cooking and Cleaning",
                taskPins = listOf(TaskPin(name = "important"), TaskPin(name = "home based")),
                taskContent = "wash the dishes and cook the dinner. after that pet the cat and feed oleg",
                status = TaskStatus.IN_PROGRESS
            )
            
            VerticalSpacer(height = 12.dp)

            TaskItem(
                taskTitle = "Cooking and Cleaning",
                taskPins = listOf(TaskPin(name = "important"), TaskPin(name = "home based")),
                taskContent = "wash the dishes and cook the dinner. after that pet the cat and feed oleg",
                status = TaskStatus.COMPLETED
            )

            VerticalSpacer(height = 12.dp)

            TaskItem(
                taskTitle = "Cooking and Cleaning",
                taskPins = listOf(TaskPin(name = "important"), TaskPin(name = "home based")),
                taskContent = "wash the dishes and cook the dinner. after that pet the cat and feed oleg",
                status = TaskStatus.CANCELLED
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    taskTitle: String = "Custom Title",
    taskContent: String = "this is task",
    taskPins: List<TaskPin> = listOf(TaskPin(), TaskPin()),
    taskDeadLine: String = "32 mins",
    status: TaskStatus = TaskStatus.IN_PROGRESS
) {
    /*TODO some remember if needed*/
    val colorGroup = rememberColorGroup(status = status)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorGroup.container,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 0.25.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(16.dp)
            )
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
                    text = taskTitle,
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
            repeat(taskPins.size) { index ->
                Pin(
                    pinTitle = taskPins[index].name,
                    backgroundColor = colorGroup.pin,
                    textColor = colorGroup.onPin
                )
            }
        }

        VerticalSpacer(height = 10.dp)

        Text(
            text = taskContent,
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
                    onClick = { /*TODO*/ }
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

@Composable
fun Pin(
    modifier: Modifier = Modifier,
    pinTitle: String = "pin",
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    textColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .then(modifier)
    ) {
        Text(
            text = pinTitle,
            style = MaterialTheme.typography.labelSmall,
            color = textColor
        )
    }
}

@Composable
fun VerticalSpacer(
    modifier: Modifier = Modifier,
    height: Dp
) {
    Spacer(modifier = Modifier
        .height(height)
        .then(modifier)
    )
}

@Composable
fun HorizontalSpacer(
    modifier: Modifier = Modifier,
    width: Dp
) {
    Spacer(modifier = Modifier
        .width(width)
        .then(modifier)
    )
}

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    height: Dp = 0.25.dp,
    color: Color = MaterialTheme.colorScheme.outlineVariant
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(
                color = color,
                shape = RectangleShape
            )
            .then(modifier)
    )
}

@Composable
fun rememberColorGroup(status: TaskStatus): TaskColors {
    val scheme = MaterialTheme.colorScheme
    return when(status) {
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
        /* TODO put the colors into material theme */
        TaskStatus.COMPLETED -> {
            TaskColors(
                container = SuccessContainer,
                onContainer = OnSuccessContainer,
                pin = Success,
                onPin = OnSuccess,
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


@Immutable
data class TaskColors(
    val container: Color,
    val onContainer: Color,
    val pin: Color,
    val onPin: Color,
    val done: Color? = null,
    val onDone: Color? = null,
    val cancel: Color? = null,
    val onCancel: Color? = null
)

@Immutable
data class TaskPin(
    /*TODO remove color and add importance(or something like that)*/
    val name: String = "pin",
    val color: Color = Color.Cyan
)

enum class TaskStatus {
    CANCELLED,IN_PROGRESS,COMPLETED
}