package com.ogorod.notepadtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ogorod.notepadtask.ui.navigation.StartApp
import com.ogorod.notepadtask.ui.theme.CustomCorner
import com.ogorod.notepadtask.ui.theme.CustomPallet
import com.ogorod.notepadtask.ui.theme.CustomSize
import com.ogorod.notepadtask.ui.theme.NotepadTaskTheme
import com.ogorod.notepadtask.ui.theme.ThemeModeSelection
import com.ogorod.notepadtask.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingsPref =
                getSharedPreferences(stringResource(id = R.string.data_settings), MODE_PRIVATE)
            val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
            mainScreenViewModel.startFillList()

            // цветовая схема, тема приложения
            val customPallet = remember {
                mutableStateOf(
                    CustomPallet.valueOf(
                        settingsPref.getString(
                            getString(R.string.PALETTE),
                            getString(R.string.PALETTE_DEFAULT)
                        ).toString()
                    )
                )
            }

            //размер шрифта
            val customSize = remember {
                mutableStateOf(
                    CustomSize.valueOf(
                        settingsPref.getString(
                            getString(R.string.SIZE),
                            getString(R.string.SIZE_DEFAULT)
                        ).toString()
                    )
                )
            }

            //форма компонентов
            val customShape = remember {
                mutableStateOf(
                    CustomCorner.valueOf(
                        settingsPref.getString(
                            getString(R.string.CORNER),
                            getString(R.string.CORNER_DEFAULT)
                        ).toString()
                    )
                )
            }

            // режим темы
            val themeMode = remember {
                mutableStateOf(
                    ThemeModeSelection.valueOf(
                        settingsPref.getString(
                            getString(R.string.THEME_MODE),
                            getString(R.string.THEME_MODE_DEFAULT)
                        ).toString()
                    )
                )
            }

            val theme = when (themeMode.value) {
                ThemeModeSelection.System -> isSystemInDarkTheme()
                ThemeModeSelection.Light -> false
                else -> true
            }

            val imageOrModifier = when (customPallet.value) {
                CustomPallet.Orange -> {
                    Modifier
                        .fillMaxSize()
                        .paint(
                            painter = if (theme) painterResource(id = R.drawable.dark_background) else painterResource(
                                id = R.drawable.background
                            ),
                            contentScale = ContentScale.FillBounds,
                            alpha = if (theme) 1f else 0.7f,
                            sizeToIntrinsics = true
                        )
                }

                else -> {
                    Modifier.fillMaxSize()
                }
            }
            val customModifier = remember(imageOrModifier) {
                mutableStateOf(imageOrModifier)
            }

            val darkTheme = remember(theme) { mutableStateOf(theme) }
            val navController = rememberNavController()

            NotepadTaskTheme(
                darkTheme = darkTheme.value,
                customPallet = customPallet.value,
                settingsTextSize = customSize.value,
                settingsShapes = customShape.value
            ) {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize()
//                ) {

                StartApp(
                    modifier = Modifier,
                    customModifier = customModifier.value,
                    navController = navController,
                    darkTheme = darkTheme.value,
                    textSizeIndex = customSize.value.ordinal,
                    shapeCornerIndex = customShape.value.ordinal,
                    customPalletIndex = customPallet.value.ordinal,
                    themeModeIndex = themeMode.value.ordinal,
                    setSizeText = {
                        settingsPref.edit().putString(getString(R.string.SIZE), it.name).apply()
                        customSize.value = it
                    },
                    setRoundingCorner = {
                        settingsPref.edit().putString(getString(R.string.CORNER), it.name)
                            .apply()
                        customShape.value = it
                    },
                    setCustomPallet = {
                        settingsPref.edit().putString(getString(R.string.PALETTE), it.name)
                            .apply()
                        customPallet.value = it
                    },
                    setThemeMode = {
                        settingsPref.edit().putString(getString(R.string.THEME_MODE), it.name)
                            .apply()
                        themeMode.value = it
                    },
                    mainScreenViewModel = mainScreenViewModel
                )
//                }
            }
        }
    }
}
