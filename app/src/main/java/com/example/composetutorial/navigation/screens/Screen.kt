package com.example.composetutorial.navigation.screens

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
const val MAIN_GRAPH_ROUTE = "main"

sealed class Screen(val route: String) {
    object LoginScreen: Screen(route = "login_screen")
    object MainScreen: Screen(route = "main_screen")
    object TermsAndConditionScreen: Screen(route = "terms_condition_screen")
}
