package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTimePickerDialog(
    initHour: Int = 12,
    initMinute: Int = 0,
    onTimeChoose: (Int,Int) -> Unit,
    onDismiss: () -> Unit
) {

    val state = rememberTimePickerState(
        is24Hour = true,
        initialHour = initHour,
        initialMinute = initMinute
    )

    Dialog(
        onDismissRequest = onDismiss
    ){
        Column(
            modifier = Modifier

                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 16.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(
                state = state
            )

            VerticalSpacer(height = 12.dp)


            RoundedCoveringButton(
                modifier = Modifier
                    .padding(horizontal = 32.dp),
                onButtonClick = {
                    onTimeChoose.invoke(state.hour,state.minute)
                    onDismiss.invoke()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.labelled_time_picker_ready),
                    style = MaterialTheme.typography.labelMedium
                )
            }

        }
    }
}