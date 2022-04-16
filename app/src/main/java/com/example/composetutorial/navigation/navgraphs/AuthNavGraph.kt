package com.example.composetutorial.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.composetutorial.navigation.screens.AUTH_GRAPH_ROUTE
import com.example.composetutorial.navigation.screens.Screen
import com.example.composetutorial.screen.LoginScreen
import com.example.composetutorial.screen.TermsConditionScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.TermsAndConditionScreen.route) {
            TermsConditionScreen(navController = navController)
        }

    }
}