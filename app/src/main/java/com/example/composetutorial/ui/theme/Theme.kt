package com.example.composetutorial.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.*


// Dark Colors
private val DarkColorPalette = CustomColors(
    backgroundColor = Purple200,
    buttonBackgroundColor = Purple700,
    buttonText = Pink600,
    textColor = Pink600,
    fabIconColor = Green700,
    friendsColor = Orange900
)

//Light Colors
private val LightColorPalette = CustomColors(
    backgroundColor = Purple500,
    buttonBackgroundColor = Teal200,
    buttonText = Pink300,
    textColor = Pink300,
    fabIconColor = Green400,
    friendsColor = Orange600
)

private val LocalColorsProvider = staticCompositionLocalOf {
    LightColorPalette
}

@Composable
private fun CustomLocalProvider(
    colors: CustomColors,
    content: @Composable() () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.updateColors(colors = colors)
    CompositionLocalProvider(LocalColorsProvider provides colorPalette, content = content)
}

private val ThemeMode.colors: Pair<Colors, CustomColors>
    get() = when (this) {
        ThemeMode.DARK -> darkColors() to DarkColorPalette
        ThemeMode.LIGHT -> darkColors() to LightColorPalette
    }

object CustomThemeManager {
    val colors: CustomColors
        @Composable
        get() = LocalColorsProvider.current

    var customTheme by mutableStateOf(ThemeMode.LIGHT)

    fun isSystemInDarkMode(): Boolean = customTheme == ThemeMode.DARK
}


@Composable
fun AppCustomComposeTheme (
    themeMode: ThemeMode = CustomThemeManager.customTheme,
    content: @Composable() () -> Unit
) {
    val (colorPalette, lcColor) = themeMode.colors

    CustomLocalProvider(colors = lcColor) {
        MaterialTheme(
            colors = colorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}