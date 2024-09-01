package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    behavior: TopAppBarScrollBehavior,
    openSearchBar: () -> Unit,
    getAllFavorites: () -> Unit,
    openSettingsScreen: () -> Unit,
    isFavorite: State<Boolean>
) {

    val expandedFavorite = rememberSaveable {
        isFavorite
    }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.name_title_topAppBar),
                style = NotepadTaskTheme.customTypography.title
            )
        },
        actions = {
            // call search bar
            IconButton(onClick = { openSearchBar() }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "search_icon"
                )
            }

            //call  all task favorite
            IconButton(onClick = {
                getAllFavorites()
            }) {
                Icon(
                    imageVector = if (expandedFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "isFavorite",
                    tint = if (expandedFavorite.value) NotepadTaskTheme.customColor.colorFavorite else NotepadTaskTheme.customColor.onSurface
                )
            }

            // call screen settings
            IconButton(onClick = { openSettingsScreen() }) {
                Icon(
                    imageVector = Icons.Default.Settings, contentDescription = "settings_icon",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = NotepadTaskTheme.customColor.tolBar,
            titleContentColor = NotepadTaskTheme.customColor.onSurface,
            scrolledContainerColor = NotepadTaskTheme.customColor.primary,
            actionIconContentColor = NotepadTaskTheme.customColor.onSurface
        ),
        scrollBehavior = behavior,
    )
}