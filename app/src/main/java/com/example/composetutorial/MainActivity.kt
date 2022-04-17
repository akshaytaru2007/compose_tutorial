package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.navigation.navgraphs.SetupNavGraph
import com.example.composetutorial.ui.theme.AppCustomComposeTheme
import com.example.composetutorial.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Abhishek: Move this file to proper architecture module specific package.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            false
        }

        setContent {
            AppCustomComposeTheme {
                val startDestination by mainActivityViewModel.startDestination
                val navController = rememberNavController()
                val bottomNavController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    bottomNavController = bottomNavController,
                    startDestination = startDestination
                )
            }
        }
    }

}






