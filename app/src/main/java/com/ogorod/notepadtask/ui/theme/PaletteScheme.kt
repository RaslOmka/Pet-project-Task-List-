package com.ogorod.notepadtask.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColor(
    val tolBar: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color = Color.Unspecified,
    val onTertiary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val transparent: Color = Color.Unspecified,
    val outline: Color = Color.Unspecified,
    val colorFavorite: Color,
    val extraBackground: Color = Color.Unspecified
)

val LocalCustomColor = staticCompositionLocalOf {
    CustomColor(
        tolBar = Color.Unspecified,
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        colorFavorite = Color.Unspecified
    )
}

//******

// LightOrange Pallet
val OrangePaletteLight = CustomColor(
    tolBar = TollBar_Orange_Light,
    primary = Primary_OrangeVisible_Alpha95_Light,
    onPrimary = OnPrimary_Orange_Light,
    secondary = Secondary_OrangeVisible_Alpha80_Light,
    onSecondary = OnSecondary_OrangeVisible_Light,
    surface = Surface_Orange_Light,
    onSurface = OnSurface_Orange_Light,
    transparent = Transparent_Orange_Light,
    tertiary = Tertiary_Orange_light,
    onTertiary = OnTertiary_Orange_light,
    outline = Outline_OrangePallet_Light,
    colorFavorite = Favorite_DarkOrange_Alpha80_light,
    background = Background_Orange_Light,
    onBackground = OnBackground_Orange_Light,
    extraBackground = ExtraBackground_Orange_Light
)

// DarkOrange Pallet
val OrangePaletteDark = CustomColor(
    tolBar = TollBar_Orange_Dark,
    primary = Primary_OrangeVisible_Alpha95_Dark,
    onPrimary = OnPrimary_Orange_Dark,
    secondary = Secondary_OrangeVisible_Alpha80_Dark,
    onSecondary = OnSecondary_OrangeVisible_Dark,
    surface = Surface_Orange_Dark,
    onSurface = OnSurface_Orange_Dark,
    transparent = Transparent_Orange_Dark,
    tertiary = Tertiary_Orange_Dark,
    onTertiary = OnTertiary_Orange_Dark,
    outline = Outline_OrangePallet_Dark,
    colorFavorite = Favorite_DarkOrange_Alpha80_Dark,
    background = Background_Dark,
    onBackground = OnBackground_Orange_Dark,
    extraBackground = ExtraBackground_Orange_Dark
)

//********

// LightBlueLite Pallet
val BlueLitePaletteLight = CustomColor(
    tolBar = TollBar_BlueLite_Light,
    primary = Primary_BlueLite_Light,
    onPrimary = OnPrimary_BlueLite_Light,
    secondary = Secondary_BlueLite_Light,
    onSecondary = OnSecondary_BlueLite_Light,
    surface = Surface_BlueLite_Light,
    onSurface = OnSurface_BlueLite_Light,
    transparent = Transparent_BlueLite_Light,
    tertiary = Tertiary_BlueLite_light,
    onTertiary = OnTertiary_BlueLite_light,
    outline = Outline_BlueLite_Light,
    colorFavorite = Favorite_BlueLite_light,
    background = Background_BlueLite_Light,
    onBackground = OnBackground_BlueLite_Light,
    extraBackground = ExtraBackground_BlueLite_Light
)

// DarkBlueLite Pallet
val BlueLitePaletteDark = CustomColor(
    tolBar = TollBar_BlueLite_Dark,
    primary = Primary_BlueLite_Dark,
    onPrimary = OnPrimary_BlueLite_Dark,
    secondary = Secondary_BlueLite_Dark,
    onSecondary = OnSecondary_BlueLite_Dark,
    surface = Surface_BlueLite_Dark,
    onSurface = OnSurface_BlueLite_Dark,
    transparent = Transparent_BlueLite_Dark,
    tertiary = Tertiary_BlueLite_Dark,
    onTertiary = OnTertiary_BlueLite_Dark,
    outline = Outline_BlueLite_Dark,
    colorFavorite = Favorite_BlueLite_Dark,
    background = Background_BlueLite_Dark,
    onBackground = OnBackground_BlueLite_Dark,
    extraBackground = ExtraBackground_BlueLite_Dark
)


//*******


// LightPicturesque Pallet
val PicturesquePaletteLight = CustomColor(
    tolBar = TollBar_Picturesque_Light,
    primary = Primary_Picturesque_Light,
    onPrimary = OnPrimary_Picturesque_Light,
    secondary = Secondary_Picturesque_Light,
    onSecondary = OnSecondary_Picturesque_Light,
    tertiary = Tertiary_Picturesque_light,
    onTertiary = OnTertiary_Picturesque_light,
    background = Background_Picturesque_Light,
    onBackground = OnBackground_Picturesque_Light,
    surface = Surface_Picturesque_Light,
    onSurface = OnSurface_Picturesque_Light,
    transparent = Transparent_Picturesque_Light,
    outline = Outline_Picturesque_Light,
    colorFavorite = Favorite_Picturesque_light,
    extraBackground = ExtraBackground_Picturesque_Light
)


// DarkPicturesque Pallet
val PicturesquePaletteDark = CustomColor(
    tolBar = TollBar_Picturesque_Dark,
    primary = Primary_Picturesque_Dark,
    onPrimary = OnPrimary_Picturesque_Dark,
    secondary = Secondary_Picturesque_Dark,
    onSecondary = OnSecondary_Picturesque_Dark,
    tertiary = Tertiary_Picturesque_Dark,
    onTertiary = OnTertiary_Picturesque_Dark,
    background = Background_Picturesque_Dark,
    onBackground = OnBackground_Picturesque_Dark,
    surface = Surface_Picturesque_Dark,
    onSurface = OnSurface_Picturesque_Dark,
    transparent = Transparent_Picturesque_Dark,
    outline = Outline_Picturesque_Dark,
    colorFavorite = Favorite_Picturesque_Dark,
    extraBackground = ExtraBackground_Picturesque_Dark
)
