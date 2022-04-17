package com.example.composetutorial.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Green400 = Color(0xFFB1ED56)
val Green700 = Color(0xFF8EC01E)
val Pink300 = Color(0xFFED56B1)
val Pink600 = Color(0xFFDB0081)
val Orange600 = Color(0xFFFFDD6F)
val Orange900 = Color(0xFFED9256)


@Stable
class CustomColors(
    backgroundColor: Color,
    buttonBackgroundColor: Color,
    buttonText: Color,
    textColor: Color,
    fabIconColor: Color,
    friendsColor: Color
) {
    var backgroundColor by mutableStateOf(backgroundColor)
        private set

    var buttonBackgroundColor by mutableStateOf(buttonBackgroundColor)
        private set

    var buttonText by mutableStateOf(buttonText)
        private set

    var textColor by mutableStateOf(textColor)
        private set

    var fabIconColor by mutableStateOf(fabIconColor)
        private set

    var friendsColor by mutableStateOf(friendsColor)
        private set

    fun updateColors(colors: CustomColors) {
        this.backgroundColor = colors.backgroundColor
        this.buttonBackgroundColor = colors.buttonBackgroundColor
        this.buttonText = colors.buttonText
        this.textColor = colors.textColor
        this.fabIconColor = colors.fabIconColor
        this.friendsColor = colors.friendsColor
    }

    fun copy() = CustomColors(
        backgroundColor,
        buttonBackgroundColor,
        buttonText,
        textColor,
        fabIconColor,
        friendsColor
    )
}