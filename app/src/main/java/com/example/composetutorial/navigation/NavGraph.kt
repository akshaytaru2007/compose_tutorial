package com.example.composetutorial.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composetutorial.screen.HomeScreen
import com.example.composetutorial.screen.LoginScreen
import com.example.composetutorial.screen.SplashScreen
import com.example.composetutorial.viewmodel.LoginViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {

            LoginScreen(navController = navController)
        }

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }
    }
}