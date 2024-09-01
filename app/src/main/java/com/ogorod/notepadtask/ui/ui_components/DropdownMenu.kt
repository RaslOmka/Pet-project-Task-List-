package com.ogorod.notepadtask.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDropdownMenu(
    modifier: Modifier,
    labelText: String,
    dropDownListOptions: MutableState<Array<String>>,
    selectedOptions: MutableState<String>,
    setOptions: (String) -> Unit
) {
    val expanded = remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = !expanded.value }
        ) {

            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .menuAnchor(),
                readOnly = true,
                value = selectedOptions.value, onValueChange = {},
                label = {
                    Text(
                        text = labelText,
                        style = NotepadTaskTheme.customTypography.label,
                        color = NotepadTaskTheme.customColor.onSurface
                    )
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)

                    DropdownMenu(
                        modifier = modifier
                            .background(NotepadTaskTheme.customColor.background)
                            .padding(horizontal = 5.dp)
//                            .background(color = NotepadTaskTheme.customColor.onBackground)
                            .fillMaxWidth(),
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false }) {
                        dropDownListOptions.value.forEach { itemOptions ->
                            DropdownMenuItem(
                                modifier = modifier.background(
                                    if (selectedOptions.value == itemOptions)
                                        NotepadTaskTheme.customColor.secondary else Color.Unspecified
                                ),
                                text = {
                                    Text(
                                        text = itemOptions,
                                        style = NotepadTaskTheme.customTypography.body
                                    )
                                },
                                onClick = {
                                    expanded.value = false
                                    setOptions(itemOptions)
                                },
                                trailingIcon = {
                                    if (itemOptions == selectedOptions.value) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "icon_check"
                                        )
                                    }
                                },
                                colors = MenuDefaults.itemColors(
                                    textColor = NotepadTaskTheme.customColor.onSurface,
                                    trailingIconColor = NotepadTaskTheme.customColor.onSurface
                                )
                            )
                        }
                    }
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = NotepadTaskTheme.customColor.transparent,
                    unfocusedBorderColor = NotepadTaskTheme.customColor.transparent,
                    focusedTextColor = NotepadTaskTheme.customColor.onSurface,
                    unfocusedTextColor = NotepadTaskTheme.customColor.onSurface,
                    focusedTrailingIconColor = NotepadTaskTheme.customColor.onSurface,
                    unfocusedTrailingIconColor = NotepadTaskTheme.customColor.onSurface
                ),
                textStyle = NotepadTaskTheme.customTypography.body,
            )
        }
    }
}