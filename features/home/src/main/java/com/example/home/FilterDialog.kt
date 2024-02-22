package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.domain.events.MainEvent
import com.example.domain.models.TaskPin
import com.example.domain.models.TaskStatus
import com.example.domain.states.FilterState
import com.example.ui.R
import com.example.util.extension.or
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onEvent: (MainEvent) -> Unit,
    state: FilterState
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()


    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.surface,
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.filters),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            VerticalSpacer(height = 16.dp)

            FilterDialogStatusItem(
                onEvent = onEvent,
                filteredStatuses = state.selectedStatuses
            )

            VerticalSpacer(height = 24.dp)

            FilterDialogPinsItem(
                onEvent = onEvent,
                items = state.allPins,
                filteredPins = state.selectedPins
            )
            /*TODO date filter*/

            VerticalSpacer(height = 32.dp)

            RoundedCoveringButton(
                onButtonClick = {
                    onEvent.invoke(MainEvent.OnSaveFilter)
                    coroutineScope.launch {
                        sheetState.hide()
                        onDismiss.invoke()
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.filter_dialog_button_ready),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            VerticalSpacer(height = 32.dp)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterDialogPinsItem(
    onEvent: (MainEvent) -> Unit,
    items: Set<TaskPin> = setOf(),
    filteredPins: List<TaskPin> = listOf()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.filter_dialog_pins_choose),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(id = R.string.filter_dialog_pins_erase),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        onEvent.invoke(MainEvent.OnErasePins)
                    }
            )
        }

        VerticalSpacer(height = 8.dp)

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(items.size) {
                PinChip(
                    value = items.elementAt(it),
                    selected = filteredPins.contains(items.elementAt(it)),
                    onClick = { taskPin, selected ->
                        onEvent.invoke(MainEvent.OnFilterPinsChanged(
                            clickedChip = taskPin,
                            selectedNow = selected
                        ))
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FilterDialogStatusItem(
    onEvent: (MainEvent) -> Unit,
    filteredStatuses: List<TaskStatus> = listOf()
) {

    val items by remember {
        mutableStateOf(TaskStatus.entries)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.filter_dialog_status_choose),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(id = R.string.filter_dialog_status_erase),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        onEvent.invoke(MainEvent.OnEraseStatuses)
                    }
            )
        }

        VerticalSpacer(height = 8.dp)

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(items.size) {
                StatusChip(
                    text = stringResource(id = items[it].value()),
                    value = items[it],
                    onClick = { status, selected ->
                        onEvent.invoke(MainEvent.OnFilterStatusChanged(
                            clickedChip = status,
                            selectedNow = selected
                        ))
                    },
                    selected = filteredStatuses.contains(items[it])
                )
            }
        }
    }
}

@Composable
private fun PinChip(
    modifier: Modifier = Modifier,
    value: TaskPin = TaskPin(),
    selected: Boolean = false,
    onClick: (TaskPin, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary.or(
                    selected, MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .border(
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(15.dp),
                width = 0.25.dp
            )
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick.invoke(value, selected)
            }
            .then(modifier)
            .padding(vertical = 4.dp, horizontal = 8.dp)

    ) {
        Text(
            text = value.name,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary.or(
                selected, MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}

@Composable
private fun StatusChip(
    modifier: Modifier = Modifier,
    value: TaskStatus = TaskStatus.IN_PROGRESS,
    text: String = "",
    selected: Boolean = false,
    onClick: (TaskStatus, Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primary.or(
                    selected, MaterialTheme.colorScheme.primaryContainer
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .border(
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(15.dp),
                width = 0.25.dp
            )
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick.invoke(value, selected)
            }
            .then(modifier)
            .padding(vertical = 4.dp, horizontal = 8.dp)

    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimary.or(
                selected, MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}