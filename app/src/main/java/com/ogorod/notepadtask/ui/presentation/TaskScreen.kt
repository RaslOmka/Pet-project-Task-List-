package com.ogorod.notepadtask.ui.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.ui_components.TopAppBarTaskScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    customModifier: Modifier,
    title: String,
    text: String,
    date: String,
    readOnly: Boolean,
    changeTitle: (String) -> Unit,
    changeText: (String) -> Unit,
    navigateBack: () -> Unit,
    saveTask: () -> Unit
) {

    val stateColumnScroll = rememberScrollState()
    val behavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val showIconSaveTask = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(stateColumnScroll.maxValue) {
//        stateColumnScroll.scrollTo(stateColumnScroll.maxValue)
        // or
        stateColumnScroll.animateScrollTo(stateColumnScroll.maxValue)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(behavior.nestedScrollConnection),
        topBar = {
            TopAppBarTaskScreen(
                saveTask = saveTask,
                restoreTaskDelete = readOnly,
                behavior = behavior,
                showSaveTask = showIconSaveTask.value,
                navigateBack = navigateBack,
                changeShowSaveTask = { showIconSaveTask.value = false }
            )
        },
        containerColor = NotepadTaskTheme.customColor.extraBackground
    ) { paddingContent ->

        Column(
            modifier = customModifier
                .padding(paddingContent)
                .verticalScroll(stateColumnScroll)
        ) {

            //поле для ввода заголовка
            TextField(
                modifier = modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.input_title),
                        fontWeight = FontWeight.Medium,
                        color = NotepadTaskTheme.customColor.onPrimary
                    )
                },
                textStyle = NotepadTaskTheme.customTypography.title.copy(fontWeight = FontWeight.Medium),
                value = title,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = NotepadTaskTheme.customColor.onSurface,
                    unfocusedTextColor = NotepadTaskTheme.customColor.onSurface,
                    focusedContainerColor = NotepadTaskTheme.customColor.transparent,
                    unfocusedContainerColor = NotepadTaskTheme.customColor.transparent,
                    selectionColors = TextSelectionColors(
                        handleColor = NotepadTaskTheme.customColor.tertiary,
                        backgroundColor = NotepadTaskTheme.customColor.onTertiary
                    ),
                    focusedIndicatorColor = NotepadTaskTheme.customColor.tertiary,
                    unfocusedIndicatorColor = NotepadTaskTheme.customColor.outline,
                    cursorColor = NotepadTaskTheme.customColor.tertiary
                ),
                onValueChange = {
                    showIconSaveTask.value = true
                    changeTitle(it)
                },
                readOnly = readOnly
            )

            //Время создания таски
            Text(
                modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp), text = date,
                style = NotepadTaskTheme.customTypography.timeSize,
                color = NotepadTaskTheme.customColor.onSurface
            )
            //разделитель линия
            Divider(
                thickness = 1.dp,
                color = NotepadTaskTheme.customColor.tertiary
            )

            //поле для ввода основного текста
            TextField(
                modifier = modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.input_text),
                        color = NotepadTaskTheme.customColor.onPrimary
                    )
                },
                textStyle = NotepadTaskTheme.customTypography.body,
                value = text,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = NotepadTaskTheme.customColor.outline,
                    unfocusedTextColor = NotepadTaskTheme.customColor.outline,
                    focusedContainerColor = NotepadTaskTheme.customColor.transparent,
                    unfocusedContainerColor = NotepadTaskTheme.customColor.transparent,
                    selectionColors = TextSelectionColors(
                        handleColor = NotepadTaskTheme.customColor.tertiary,
                        backgroundColor = NotepadTaskTheme.customColor.onTertiary
                    ),
                    focusedIndicatorColor = NotepadTaskTheme.customColor.tertiary,
                    unfocusedIndicatorColor = NotepadTaskTheme.customColor.outline,
                    cursorColor = NotepadTaskTheme.customColor.tertiary
                ),
                onValueChange = {
                    showIconSaveTask.value = true
                    changeText(it)
                },
                readOnly = readOnly
            )
        }
    }
}