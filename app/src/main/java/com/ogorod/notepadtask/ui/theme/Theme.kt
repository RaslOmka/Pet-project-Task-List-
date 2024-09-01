package com.ogorod.notepadtask.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

enum class CustomPallet(
    val containerColorLight: Color = Color.Unspecified,
    val borderColorLight: Color = Color.Unspecified,
    val containerColorDark: Color = Color.Unspecified,
    val borderColorDark: Color = Color.Unspecified
) {
    Orange(
        OrangePaletteLight.surface,
        OrangePaletteLight.tertiary,
        OrangePaletteDark.surface,
        OrangePaletteDark.colorFavorite
    ),

    BlueLite(
        BlueLitePaletteLight.surface,
        BlueLitePaletteLight.colorFavorite,
        BlueLitePaletteDark.surface,
        BlueLitePaletteDark.colorFavorite
    ),

    Picturesque(
        PicturesquePaletteLight.surface,
        PicturesquePaletteLight.colorFavorite,
        PicturesquePaletteDark.surface,
        PicturesquePaletteDark.colorFavorite
    )
}

enum class CustomSize {
    Large, Medium, Small
}

enum class CustomCorner {
    Large, Medium, Flat
}

enum class ThemeModeSelection {
    System, Light, Dark
}

@Composable
fun NotepadTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //dynamicColor: Boolean = false,
    customPallet: CustomPallet,
    settingsTextSize: CustomSize,
    settingsShapes: CustomCorner,
    content: @Composable () -> Unit
) {
    val colorScheme = when (customPallet) {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        CustomPallet.Orange -> if (darkTheme) OrangePaletteDark else OrangePaletteLight
        CustomPallet.BlueLite -> if (darkTheme) BlueLitePaletteDark else BlueLitePaletteLight
        CustomPallet.Picturesque -> if (darkTheme) PicturesquePaletteDark else PicturesquePaletteLight

    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            window.navigationBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    val typography = CustomTypography(
        body = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (settingsTextSize) {
                CustomSize.Large -> 18.sp  //16
                CustomSize.Medium -> 16.sp  //14
                CustomSize.Small -> 14.sp   //12
            },
            lineHeight = when (settingsTextSize) {
                CustomSize.Large -> 26.sp  //24
                CustomSize.Medium -> 24.sp  //20
                CustomSize.Small -> 20.sp   //15
            },
            letterSpacing = when (settingsTextSize) {
                CustomSize.Large -> 0.8.sp  //0.5
                CustomSize.Medium -> 0.5.sp  //0.2
                CustomSize.Small -> 0.3.sp   //0.4
            },
        ),
        title = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (settingsTextSize) {
                CustomSize.Large -> 22.sp   //22
                CustomSize.Medium -> 18.sp  //16
                CustomSize.Small -> 15.sp   //14
            },
            lineHeight = when (settingsTextSize) {
                CustomSize.Large -> 26.sp   //28
                CustomSize.Medium -> 23.sp  //24
                CustomSize.Small -> 20.sp   //20
            },
            letterSpacing = when (settingsTextSize) {
                CustomSize.Large -> 0.1.sp  //0
                CustomSize.Medium -> 0.2.sp  //0.2
                CustomSize.Small -> 0.5.sp  //0.1
            },
        ),
        label = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = when (settingsTextSize) {
                CustomSize.Large -> 16.sp //14
                CustomSize.Medium -> 14.sp  //12
                CustomSize.Small -> 12.sp   //11
            },
            lineHeight = when (settingsTextSize) {
                CustomSize.Large -> 24.sp  //20
                CustomSize.Medium -> 20.sp  //16
                CustomSize.Small -> 16.sp   //14
            },
            letterSpacing = when (settingsTextSize) {
                CustomSize.Large -> 0.1.sp //0.1
                CustomSize.Medium -> 0.2.sp  //0.2
                CustomSize.Small -> 0.5.sp  //0.5
            },
        ),
        timeSize = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = when (settingsTextSize) {
                CustomSize.Large -> 16.sp  //16
                CustomSize.Medium -> 13.sp  //14
                CustomSize.Small -> 10.sp   //12
            },
            lineHeight = when (settingsTextSize) {
                CustomSize.Large -> 22.sp  //24
                CustomSize.Medium -> 18.sp  //20
                CustomSize.Small -> 14.sp   //15
            },
            letterSpacing = when (settingsTextSize) {
                CustomSize.Large -> 0.5.sp  //0.5
                CustomSize.Medium -> 0.3.sp  //0.2
                CustomSize.Small -> 0.2.sp   //0.4
            },
        )
    )

    val shapes = CustomShapes(
        cornerCard = when (settingsShapes) {
            CustomCorner.Large -> RoundedCornerShape(18)
            CustomCorner.Medium -> RoundedCornerShape(8)
            CustomCorner.Flat -> RoundedCornerShape(0)
        },
        cornerFAB = when (settingsShapes) {
            CustomCorner.Large -> RoundedCornerShape(36)
            CustomCorner.Medium -> RoundedCornerShape(28)
            CustomCorner.Flat -> RoundedCornerShape(12)
        }
    )

    CompositionLocalProvider(
        LocalCustomColor provides colorScheme,
        LocalCustomTypography provides typography,
        LocalCustomShapes provides shapes,
        content = content
    )
}

object NotepadTaskTheme {
    val customColor: CustomColor
        @Composable
        get() = LocalCustomColor.current
    val customTypography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current
    val customShapes: CustomShapes
        @Composable
        get() = LocalCustomShapes.current
}