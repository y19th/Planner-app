package com.example.planner_app.presentation.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner_app.R
import com.example.planner_app.presentation.components.Divider
import com.example.planner_app.ui.theme.onSuccessVariant

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.setting_screen),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(vertical = 32.dp, horizontal = 20.dp)
        ) {
            SettingsPart(title = stringResource(id = R.string.settings_theme)) {

            }
        }
    }
}

@Composable
fun SettingsPart(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSuccessVariant
        )

        Divider(modifier = Modifier.padding(vertical = 2.dp))

        content.invoke()
    }
}


@Preview(
    showBackground = true
//    showSystemUi = true
)
@Composable
fun PreviewOutlinedTextField() {
    Box(modifier = Modifier.padding(all = 20.dp)) {
        MainTextField(
            onValueChange = {},
            onDropDownClick = {},
            value = "Light theme",
            labelText = "Dark theme"
        )
    }
}


@Composable
fun MainTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    labelText: String = "",
    isError: Boolean = false,
    onDropDownClick: @Composable () -> Unit,
    onValueChange: (String) -> Unit
) {
    var isExposed by remember {
        mutableStateOf(false)
    }

    val trailingAnimation by animateFloatAsState(
        targetValue = if(isExposed) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = 400)
    )

    with(MaterialTheme.colorScheme) {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(5.dp),
            readOnly = true,
            isError = isError,
            label = {
                if(labelText.isNotEmpty())
                    Text(
                        text = labelText,
                        style = MaterialTheme.typography.bodyMedium,
                    )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            isExposed = isExposed.not()
                        }
                        .rotate(trailingAnimation)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = outline,
                focusedBorderColor = primary,
                errorBorderColor = error,
                unfocusedLabelColor = onSurfaceVariant,
                focusedLabelColor = primary,
                errorLabelColor = error,
                unfocusedTrailingIconColor = onSurfaceVariant,
                focusedTrailingIconColor = primary,
                errorTrailingIconColor = error
            )
        )

        /*DropdownMenu(
            expanded = isExposed,
            onDismissRequest = { isExposed = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            (0..4).forEach {
                DropdownMenuItem(
                    text = { Text(text = "Item $it") },
                    onClick = { *//*TODO*//* }
                )
                //Text(text = "Item$it")

            }
        }*/

    }
}








/*@Preview(
    showSystemUi = true,
    backgroundColor = 0xFF020035
)*/
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}