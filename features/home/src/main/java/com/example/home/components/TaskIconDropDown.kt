package com.example.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.components.R
import com.example.domain.events.AddEvents
import com.example.domain.events.MainEvent
import com.example.util.extension.vector

@Composable
fun TaskIconDropDown(
    @DrawableRes iconRes: Int,
    modelId: Int,
    onEvent: (MainEvent) -> Unit,
    onChangeTaskEvent: () -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column {
        Icon(
            imageVector = vector(res = iconRes),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    expanded = expanded.not()
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = R.string.task_change),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                onClick = {
                    onChangeTaskEvent.invoke()
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(id = R.string.task_delete),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                onClick = {
                    onEvent.invoke(MainEvent.OnTaskDelete(taskId = modelId))
                    expanded = false
                }
            )
        }
    }
}
