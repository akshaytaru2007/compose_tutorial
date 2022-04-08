package com.example.composetutorial.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen(route = "splash_screen")
    object LoginScreen: Screen(route = "login_screen")
    object HomeScreen: Screen(route = "home_screen")
}
