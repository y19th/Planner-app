package com.example.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.components.TextDropDown
import com.example.components.MainDivider
import com.example.domain.events.MainEvent
import com.example.domain.models.ElapsedTime
import com.example.domain.models.droppable.Filter
import com.example.home.components.TaskItem
import com.example.home.viewmodels.MainViewModel
import com.example.ui.R
import com.example.ui.theme.local.LocalSnackBarHost
import com.example.util.AnimationDuration
import com.example.util.extension.or

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.collectAsState().value
    val snackbarHost = LocalSnackBarHost.current
    val coroutineScope = rememberCoroutineScope()
    val filterItems = rememberFilterItems()

    LaunchedEffect(null) {
        viewModel.onEvent(MainEvent.OnRefresh)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.daily_planner),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        MainDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp, bottom = 32.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextDropDown(
                value = state.selectedFilter,
                iconRes = R.drawable.ic_filter_arrow_bottom,
                dropDownList = filterItems,
                onDropDownClick = {
                    viewModel.onEvent(MainEvent.OnTaskFilterChanged(it))
                }
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    /*TODO replace with correct icon*/
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(id = R.string.filters),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            items(
                items = state.taskList,
                key = { it.id }
            ) {
                TaskItem(
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(AnimationDuration.Fast)
                    ),
                    model = it,
                    onEvent = viewModel::onEvent,
                    deadLine = calculateDateDiff(
                        viewModel.calculateDateDiff(it)
                    ),
                    onChangeTaskEvent = {
                        viewModel.onEvent(MainEvent.OnTaskChange(
                            navController = navController,
                            taskId = it.id
                        ))
                    }
                )
            }
        }
    }
}

@Composable
fun calculateDateDiff(elapsedTime: ElapsedTime): String {
    val strDay = pluralStringResource(
        id = R.plurals.day,
        count = elapsedTime.days.toInt(),
        elapsedTime.days.toInt()
    ).or(elapsedTime.days != 0L, "")

    val strHour = pluralStringResource(
        id = R.plurals.hour,
        count = elapsedTime.hours.toInt(),
        elapsedTime.hours.toInt()
    ).or(elapsedTime.hours != 0L, "")

    val strMinute = pluralStringResource(
        id = R.plurals.minute,
        count = elapsedTime.minute.toInt(),
        elapsedTime.minute.toInt()
    ).or(elapsedTime.minute != 0L, "")

    val strSecond = pluralStringResource(
        id = R.plurals.second,
        count = elapsedTime.seconds.toInt(),
        elapsedTime.seconds.toInt()
    ).or(elapsedTime.seconds != 0L, "")

    if(strDay.isNotEmpty()) return rememberSaveable(elapsedTime) {
        "$strDay $strHour"
    }

    if(strHour.isNotEmpty()) return rememberSaveable(elapsedTime) {
        "$strHour $strMinute"
    }

    return rememberSaveable(elapsedTime) {
        "$strMinute $strSecond"
    }
}


@Stable
@Composable
fun rememberFilterItems(): List<Filter> {
    return rememberSaveable {
        Filter.receiveAll()
    }
}