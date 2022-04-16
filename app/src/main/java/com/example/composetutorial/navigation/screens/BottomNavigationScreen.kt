package com.example.composetutorial.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedContentColor: Color,
    val unselectedContentColor: Color

) {
    object HomeScreen : BottomNavigationScreen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray
    )

    object AllDrugScreen : BottomNavigationScreen(
        route = "all_drugs_screen",
        title = "All Drugs",
        icon = Icons.Default.Add,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray
    )

    object CalculatorScreen : BottomNavigationScreen(
        route = "calculator_screen",
        title = "Calculator",
        icon = Icons.Default.Star,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray
    )

    object FriendsScreen : BottomNavigationScreen(
        route = "friends_screen",
        title = "Friends",
        icon = Icons.Default.Person,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray
    )

    object HomeInternalScreen : BottomNavigationScreen(
        route = "home_internal_screen",
        title = "Home Internal Screen",
        icon = Icons.Default.Add,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray
    )

    object AddFriendScreen : BottomNavigationScreen(
        route = "add_friend_screen",
        title = "Add Friend Screen",
        icon = Icons.Default.AddCircle,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray
    )
}
