package com.ogorod.notepadtask.ui.ui_components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.domain.dto.TaskDTO
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBar(
    modifier: Modifier,
    customModifier: Modifier,
    taskList: State<List<TaskDTO>>,
    openSearchBar: () -> Unit,
    taskInputSearch: (String) -> Unit,
    deleteTaskOnSearchBar: (task: TaskDTO) -> Unit,
    favoriteChangeTask: (task: TaskDTO) -> Unit,
    checkIsFavorite: () -> Unit,
    getItemTaskDTO: (task: TaskDTO) -> Unit
) {

    val textSearch = rememberSaveable { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }

    //переопределить экранную кнопку "НАЗАД"
    BackHandler {
        taskInputSearch("")
        checkIsFavorite()
        openSearchBar()
    }

    SearchBar(
        modifier = modifier.fillMaxWidth(),
        query = textSearch.value,
        onQueryChange = {
            textSearch.value = it
            taskInputSearch(textSearch.value)
        },
        onSearch = {
            active.value = false
            taskInputSearch(textSearch.value)
        },
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_input_field),
                color = NotepadTaskTheme.customColor.outline
            )
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    taskInputSearch("")
                    checkIsFavorite()
                    openSearchBar()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "button_back_search"
            )
        },
        trailingIcon = {
            if (textSearch.value.isNotEmpty())
                Icon(
                    modifier = Modifier.clickable {
                        textSearch.value = ""
                        taskInputSearch("")
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "button_close_search"
                )
        },
        colors = SearchBarDefaults.colors(
            containerColor = NotepadTaskTheme.customColor.secondary,
            dividerColor = NotepadTaskTheme.customColor.outline,
            inputFieldColors = SearchBarDefaults.inputFieldColors(
                focusedTextColor = NotepadTaskTheme.customColor.outline,
                unfocusedTrailingIconColor = NotepadTaskTheme.customColor.outline,
                cursorColor = NotepadTaskTheme.customColor.tertiary,
                selectionColors = TextSelectionColors(
                    handleColor = NotepadTaskTheme.customColor.tertiary,
                    backgroundColor = NotepadTaskTheme.customColor.onTertiary
                ),
                focusedLeadingIconColor = NotepadTaskTheme.customColor.onSurface,
                unfocusedLeadingIconColor = NotepadTaskTheme.customColor.onSurface,
                focusedTrailingIconColor = NotepadTaskTheme.customColor.onSurface,
                unfocusedTextColor = NotepadTaskTheme.customColor.onSurface
            )
        )
    ) {
        TasksLazyColumn(
            modifier = modifier,
            customModifier = customModifier,
            paddingValues = PaddingValues(start = 4.dp, end = 4.dp),
            stateTaskList = taskList,
            deleteTaskDTO = { deleteTaskOnSearchBar(it) },
            changeFavoriteTaskDTO = { favoriteChangeTask(it) },
            getItemTaskDTO = { getItemTaskDTO(it) }
        )
    }
}
