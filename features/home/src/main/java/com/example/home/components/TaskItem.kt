package com.example.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.components.HorizontalSpacer
import com.example.components.MainDivider
import com.example.components.Pin
import com.example.components.VerticalSpacer
import com.example.domain.models.TaskModel
import com.example.domain.models.TaskStatus
import com.example.home.rememberColorGroup
import com.example.ui.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    model: TaskModel = TaskModel(),
    taskDeadLine: String = "32 mins",
    onDoneClick: () -> Unit
) {
    val colorGroup = rememberColorGroup(status = model.status)

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
                if(model.status == TaskStatus.IN_PROGRESS) {
                    Text(
                        text = stringResource(id = R.string.deadline_pattern, taskDeadLine),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }


        }

        MainDivider()

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

        if(model.status == TaskStatus.IN_PROGRESS) {

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
                    onClick = onDoneClick
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