package com.example.planner_app.presentation.screens

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.planner_app.R
import com.example.planner_app.presentation.components.Divider
import com.example.planner_app.presentation.components.VerticalSpacer

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
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(vertical = 24.dp, horizontal = 20.dp)
        ) {
            SettingsPart(title = stringResource(id = R.string.settings_theme)) {
                DropDownTextField(
                    value = stringResource(id = ThemeSetting.Light.nameId),
                    labelText = stringResource(id = R.string.setting_title_theme),
                    dropDownItemsList = receiveThemeListValues(),
                    onDropDownClick = { /*TODO*/ },
                    onValueChange = {

                    }
                )

                VerticalSpacer(height = 8.dp)

                DropDownTextField(
                    value = "Red color",
                    labelText = "Color",
                    onDropDownClick = { /*TODO*/ },
                    onValueChange = {

                    }
                )
            }
            
            VerticalSpacer(height = 16.dp)
            
            SettingsPart(title = stringResource(id = R.string.settings_caching)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.setting_cache_size),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )   
                    Text(
                        text = stringResource(id = R.string.setting_cache_format, 52.4f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
                
                VerticalSpacer(height = 12.dp)

                RoundedCoveringButton(
                    onButtonClick = {
                        /*TODO*/
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.setting_cache_clear),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun RoundedCoveringButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    content: @Composable (RowScope) -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentPadding = PaddingValues(all = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(15.dp),
        onClick = onButtonClick,
        content = content
    )
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
            .padding(vertical = 8.dp)
            .then(modifier)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Divider(modifier = Modifier.padding(vertical = 2.dp))

        VerticalSpacer(height = 16.dp)

        content.invoke()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    modifier: Modifier = Modifier,
    value: String,
    labelText: String = "",
    isError: Boolean = false,
    dropDownItemsList: List<String> = listOf(),
    onDropDownClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    var isExposed by remember {
        mutableStateOf(false)
    }

    var fieldValue by remember {
        mutableStateOf(value)
    }

    val trailingAnimation by animateFloatAsState(
        targetValue = if(isExposed) 180f else 0f,
        label = "trailing animation",
        animationSpec = tween(durationMillis = 400)
    )

    with(MaterialTheme.colorScheme) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = isExposed,
            onExpandedChange = {
                isExposed = isExposed.not()
            }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
                    .then(modifier),
                value = fieldValue,
                onValueChange = onValueChange,
                shape = RoundedCornerShape(5.dp),
                readOnly = true,
                isError = isError,
                textStyle = MaterialTheme.typography.bodyLarge,
                label = {
                    if (labelText.isNotEmpty())
                        Text(
                            text = labelText,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.rotate(trailingAnimation)
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
                    errorTrailingIconColor = error,
                    unfocusedTextColor = onSurfaceVariant,
                    focusedTextColor = onSurfaceVariant,
                    errorTextColor = error
                )
            )
            ExposedDropdownMenu(
                expanded = isExposed,
                onDismissRequest = { isExposed = false },
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .border(
                        width = 0.5.dp,
                        color = outline
                    )
            ) {
                dropDownItemsList.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ) },
                        onClick = {
                            fieldValue = item
                            onDropDownClick.invoke()
                            isExposed = false
                        },
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
            }
        }


    }
}

@Stable
@Composable
fun receiveThemeListValues(): List<String> {
    return ThemeList.themes.map {
        stringResource(id = it.nameId)
    }
}

private object ThemeList {
    val themes: List<ThemeSetting> = listOf(
        ThemeSetting.Light,
        ThemeSetting.Dark,
        ThemeSetting.System
    )
}

sealed class ThemeSetting(@StringRes val nameId: Int) {

    data object Light: ThemeSetting(nameId = R.string.setting_light_theme)

    data object Dark: ThemeSetting(nameId = R.string.setting_dark_theme)

    data object System: ThemeSetting(nameId = R.string.setting_system_theme)
}