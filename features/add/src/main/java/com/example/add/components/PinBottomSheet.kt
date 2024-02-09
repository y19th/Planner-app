package com.example.add.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.add.LabelledTextDropDown
import com.example.add.LabelledTextField
import com.example.add.viewmodels.AddViewModel
import com.example.components.HorizontalSpacer
import com.example.components.Pin
import com.example.domain.events.AddEvents
import com.example.domain.models.Importance
import com.example.ui.R
import com.example.util.extension.toColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinBottomSheet(
    viewModel: AddViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.pinState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LabelledTextField(
                value = state.title,
                label = stringResource(id = R.string.label_title_pin),
                onValueChange = {
                    viewModel.onEvent(AddEvents.OnTaskTitleChange(newValue = it))
                }
            )
            LabelledTextDropDown(
                value = stringResource(id = state.importance.string()),
                label = stringResource(id = R.string.label_importance_pin),
                onDropDownClick = {
                    viewModel.onEvent(
                        AddEvents.OnPinImportanceChange(newValue = it)
                    )
                },
                dropDownItems = rememberImportanceItems(),
            )
            /*TODO color drop*/

            Row {
                Text(
                    text = stringResource(id = R.string.label_preview_pin),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                HorizontalSpacer(width = 12.dp)

                Pin(
                    pinTitle = state.title,
                    backgroundColor = state.color.toColor()
                )
            }
        }
    }
}

@Composable
private fun rememberImportanceItems(): List<Importance> {
    return Importance.receiveAll()
}