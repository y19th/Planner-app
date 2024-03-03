package com.example.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.HorizontalSpacer
import com.example.components.LabelledTextDropDown
import com.example.components.MainDivider
import com.example.components.MainTopBar
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.domain.events.SettingEvents
import com.example.domain.models.TaskStatus
import com.example.domain.models.droppable.Theme
import com.example.settings.viewmodels.SettingViewModel
import com.example.ui.R
import com.example.util.AnimationDuration

@Composable
fun SettingsScreen(
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var expandedStat by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(null) {
        viewModel.onEvent(SettingEvents.OnRefreshTasks)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MainTopBar(
            title = stringResource(id = R.string.screen_settings)
        )

        LabelledTextDropDown(
            label = stringResource(id = R.string.label_settings_theme),
            value = stringResource(state.theme.stringId()),
            dropDownItems = rememberThemeList(),
            onDropDownClick = {
                viewModel.onEvent(SettingEvents.OnThemeChange(it))
            }
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.label_settings_cache),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            VerticalSpacer(height = 4.dp)

            Text(
                text = stringResource(id = R.string.label_settings_cache_desc),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            VerticalSpacer(height = 16.dp)

            LabelledExpandedList(
                label = stringResource(id = R.string.label_settings_tasks_for_year),
                expanded = expandedStat,
                onDismiss = { expandedStat = expandedStat.not() }
            ) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    ExpandedItem(
                        title = stringResource(id = R.string.label_settings_tasks_all),
                        value = state.tasks.size.toString()
                    )
                    ExpandedItem(
                        title = stringResource(id = R.string.label_settings_tasks_this_year),
                        value = state.tasks.size.toString(),
                        withBottomDivider = false
                    )

                    VerticalSpacer(height = 16.dp)

                    ExpandedItem(
                        title = stringResource(id = R.string.label_settings_tasks_success),
                        value = state.tasks.filter { model ->
                            model.status == TaskStatus.COMPLETED
                        }.size.toString()
                    )
                    ExpandedItem(
                        title = stringResource(id = R.string.label_settings_tasks_cancelled),
                        value = state.tasks.filter { model ->
                            model.status == TaskStatus.CANCELLED
                        }.size.toString(),
                        withBottomDivider = false
                    )
                }
            }

            VerticalSpacer(height = 24.dp)

            RoundedCoveringButton(
                onButtonClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.label_settings_cache_clear),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun ExpandedItem(
    modifier: Modifier = Modifier,
    title: String = "title",
    value: String = "value",
    withBottomDivider: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        HorizontalSpacer(width = 4.dp)

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }

    if (withBottomDivider) MainDivider()
}


@Composable
fun LabelledExpandedList(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    expanded: Boolean = false,
    label: String = "label",
    content: @Composable ColumnScope.() -> Unit
) {

    val trailingAnimation by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = AnimationDuration.Medium)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                onDismiss.invoke()
            }
            .padding(horizontal = 6.dp, vertical = 8.dp)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.rotate(trailingAnimation)
            )
        }
        AnimatedVisibility(visible = expanded) {
            content.invoke(this@Column)
        }
    }
}



@Composable
fun rememberThemeList(): List<Theme> {
    return rememberSaveable {
        Theme.receiveAll()
    }
}
