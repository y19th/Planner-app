package com.example.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.add.components.ColorPickerBottomSheet
import com.example.add.viewmodels.AddViewModel
import com.example.components.HorizontalSpacer
import com.example.components.LabelledTextDropDown
import com.example.components.LabelledTextField
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.domain.events.AddEvents
import com.example.domain.models.Importance
import com.example.domain.models.TaskPin
import com.example.ui.R
import com.example.ui.theme.Success
import com.example.util.extension.toColor

@Composable
fun PinAddScreen(
    viewModel: AddViewModel = hiltViewModel(),
    navController: NavController,
    pinId: String = ""
) {
    val state by viewModel.pinState.collectAsState()
    var dialogExposed by rememberSaveable {
        mutableStateOf(false)
    }

    val isEditMode by rememberSaveable(pinId) {
        mutableStateOf(pinId != TaskPin.DefaultId)
    }

    LaunchedEffect(pinId) {
        if (isEditMode) {
            viewModel.setPinState(pinId)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box (
            modifier = Modifier.fillMaxWidth()
        ){
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        if (isEditMode) viewModel.onEvent(AddEvents.OnPinNavigateUp(navController))
                        else navController.navigateUp()
                    },
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    id = if (isEditMode) R.string.edit_pin else R.string.new_pin
                ),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }

        VerticalSpacer(height = 36.dp)

        LabelledTextField(
            value = state.title,
            label = stringResource(id = R.string.label_title_pin),
            onValueChange = {
                viewModel.onEvent(AddEvents.OnPinTitleChange(newValue = it))
            }
        )
        LabelledTextDropDown(
            value = stringResource(id = state.importance.stringId()),
            label = stringResource(id = R.string.label_importance_pin),
            onDropDownClick = {
                viewModel.onEvent(
                    AddEvents.OnPinImportanceChange(newValue = it)
                )
            },
            dropDownItems = rememberImportanceItems(),
        )

        Column {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .clickable { dialogExposed = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.color_choose),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            AnimatedVisibility(
                visible = state.backgroundColor != Color.Unspecified.toArgb()
            ) {

                VerticalSpacer(height = 4.dp)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.color_choose_end),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.outline
                    )

                    HorizontalSpacer(width = 4.dp)

                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                color = state.backgroundColor.toColor(),
                                shape = RoundedCornerShape(2.dp)
                            )
                    )
                }
            }
        }

        RoundedCoveringButton(
            enabled = state.title.isNotEmpty() && state.backgroundColor != Color.Unspecified.toArgb(),
            onButtonClick = {
                if(isEditMode) {
                    viewModel.onEvent(AddEvents.OnPinUpdate(
                        pinId = pinId,
                        navController = navController
                    ))
                } else {
                    viewModel.onEvent(AddEvents.OnPinAddition(navController))
                }
            }
        ) {
            Text(
                text = stringResource(
                    id = if(isEditMode) R.string.pin_edit else R.string.pin_add
                ),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        if(isEditMode) {
            RoundedCoveringButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                ),
                onButtonClick = {
                    viewModel.onEvent(AddEvents.OnPinDelete(
                        navController = navController,
                        pinId = pinId
                    ))
                }
            ) {
                Text(
                    text = stringResource(id = R.string.pin_delete),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
        if(dialogExposed) {
            ColorPickerBottomSheet(
                initialColor = Success,
                onColorChange = {
                    viewModel.onEvent(
                        AddEvents.OnPinColorChange(newValue = it.toArgb())
                    )
                },
                onDismiss = {
                    dialogExposed = false
                }
            )
        }
    }
}

@Stable
@Composable
private fun rememberImportanceItems(): List<Importance> {
    return remember {
        Importance.receiveAll()
    }
}
