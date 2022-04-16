package com.example.composetutorial.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composetutorial.navigation.screens.MAIN_GRAPH_ROUTE
import com.example.composetutorial.navigation.screens.Screen
import com.example.composetutorial.screen.MainScreen

fun NavGraphBuilder.mainNavGraph(
    bottomNavController: NavHostController
) {
    navigation(
        startDestination = Screen.MainScreen.route,
        route = MAIN_GRAPH_ROUTE
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = bottomNavController)
        }


    }
}