package com.example.composetutorial.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.composetutorial.navigation.screens.ROOT_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    bottomNavController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = ROOT_GRAPH_ROUTE
    ) {

        authNavGraph(navController = navController)
        mainNavGraph(bottomNavController = bottomNavController)
    }
}