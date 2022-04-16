package com.example.composetutorial.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composetutorial.navigation.screens.BottomNavigationScreen
import com.example.composetutorial.screen.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreen.HomeScreen.route,
    ) {
        composable(route = BottomNavigationScreen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(route = BottomNavigationScreen.AllDrugScreen.route) {
            AllDrugs(navController = navController)
        }

        composable(route = BottomNavigationScreen.CalculatorScreen.route) {
            CalculatorScreen(navController = navController)
        }

        composable(route = BottomNavigationScreen.FriendsScreen.route) {
            FriendsScreen(navController = navController)
        }

        composable(route = BottomNavigationScreen.HomeInternalScreen.route) {
            HomeInternalScreen(navController = navController)
        }

        composable(route = BottomNavigationScreen.AddFriendScreen.route) {
            AddFriendScreen(navController = navController)
        }
    }
}