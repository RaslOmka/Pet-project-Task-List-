package com.ogorod.notepadtask.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ogorod.notepadtask.R
import com.ogorod.notepadtask.ui.theme.CustomCorner
import com.ogorod.notepadtask.ui.theme.CustomPallet
import com.ogorod.notepadtask.ui.theme.CustomSize
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.ui_components.MainDropdownMenu
import com.ogorod.notepadtask.ui.ui_components.SettingsTopBar

@Composable
fun SettingsScreen(
    modifier: Modifier,
    darkTheme: Boolean,
    themeModeIndex: Int,
    textSizeIndex: Int,
    shapeCornerIndex: Int,
    customPalletIndex: Int,
    setSizeText: (CustomSize) -> Unit,
    setRoundingCorner: (CustomCorner) -> Unit,
    setCustomPallet: (CustomPallet) -> Unit,
    navigateToDialogScreenThemeMode: () -> Unit,
    navigateToTaskRemoteScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    // список значений для выбораразмера шрифта
    val listOptionsTextSize = stringArrayResource(id = R.array.array_size_text)
    val dropDownOptionsTextSize = rememberSaveable {
        mutableStateOf(listOptionsTextSize)
    }
    //выбранное значение размера шрифта
    val selectedOptionsTextSize = rememberSaveable(textSizeIndex) {
        mutableStateOf(dropDownOptionsTextSize.value[textSizeIndex])
    }
    // список значений для выбора закругления
    val listOptionsShapeCorner = stringArrayResource(id = R.array.array_shape_corner)
    val dropDownOptionsShapeCorner = rememberSaveable {
        mutableStateOf(listOptionsShapeCorner)
    }
    //выбранное значение закругления
    val selectedOptionsShapeCorner = rememberSaveable(shapeCornerIndex) {
        mutableStateOf(dropDownOptionsShapeCorner.value[shapeCornerIndex])
    }
    // выбранная палетка
    val selectedPaletteShem = remember(customPalletIndex) {
        mutableStateOf(customPalletIndex)
    }

    val verticalScrollState = rememberScrollState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            SettingsTopBar(
                navigateBack = navigateBack,
                navigateToTaskRemoteScreen = navigateToTaskRemoteScreen
            )
        },
        containerColor = NotepadTaskTheme.customColor.background,
        contentColor = NotepadTaskTheme.customColor.onSurface
    )
    { paddingValues ->
        Column(
            modifier = modifier
                .padding(vertical = 12.dp, horizontal = 8.dp)
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
                .verticalScroll(verticalScrollState)
        ) {
            //Режим темы:
            Row(
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
                    .clickable { navigateToDialogScreenThemeMode() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = modifier.padding(8.dp),
                    text = stringResource(id = R.string.theme_mode),
                    style = NotepadTaskTheme.customTypography.title
                )
                Text(
                    modifier = modifier.padding(5.dp),
                    text = stringArrayResource(id = R.array.array_theme_mode)[themeModeIndex],
                    style = NotepadTaskTheme.customTypography.body,
                )
            }

            // разделитель
            Divider(
                modifier = modifier.padding(horizontal = 5.dp),
                color = NotepadTaskTheme.customColor.colorFavorite
            )

            // выбор размер шрфит
            MainDropdownMenu(
                modifier = modifier,
                labelText = stringResource(id = R.string.size_text),
                dropDownListOptions = dropDownOptionsTextSize,
                selectedOptions = selectedOptionsTextSize,
                setOptions = {
                    setSizeText(
                        when (it) {
                            listOptionsTextSize[0] -> CustomSize.Large
                            listOptionsTextSize[2] -> CustomSize.Small
                            else -> CustomSize.Medium
                        }
                    )
                }
            )
            // разделитель
            Divider(
                modifier = modifier.padding(horizontal = 5.dp),
                color = NotepadTaskTheme.customColor.colorFavorite
            )

            //выбор формы закругления
            MainDropdownMenu(
                modifier = modifier,
                labelText = stringResource(id = R.string.rounding_corner),
                dropDownListOptions = dropDownOptionsShapeCorner,
                selectedOptions = selectedOptionsShapeCorner,
                setOptions = {
                    setRoundingCorner(
                        when (it) {
                            listOptionsShapeCorner[0] -> CustomCorner.Large
                            listOptionsShapeCorner[2] -> CustomCorner.Flat
                            else -> CustomCorner.Medium
                        }
                    )
                }
            )

            // разделитель
            Divider(
                modifier = modifier.padding(horizontal = 5.dp),
                color = NotepadTaskTheme.customColor.colorFavorite
            )

            // выбор стиля цветовой схемы (тема)
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        modifier = modifier.padding(start = 5.dp, top = 5.dp),
                        text = stringResource(id = R.string.style_color),
                        style = NotepadTaskTheme.customTypography.title
                    )
                }

                Spacer(modifier = modifier.padding(top = 20.dp))

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
//                        .background(NotepadTaskTheme.customColor.onBackground),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CustomPallet.values().forEach { customColor ->
                        TemplatePalette(customColor,
                            darkTheme = darkTheme,
                            paletteCurrent = selectedPaletteShem.value,
                            setCustomPallet = { setCustomPallet(it) })
                    }
                }
                Spacer(modifier = modifier.padding(bottom = 20.dp))
            }

//            // предварительный просмотр карточки
//            PreviewCardPalette()

        }
    }
}
