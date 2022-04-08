package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.navigation.SetupNavGraph
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import com.example.composetutorial.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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

            ComposeTutorialTheme {
                val startDestination by mainActivityViewModel.startDestination
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    startDestination = startDestination
                )
            }

//            Column(modifier = Modifier
//                .background(color = Color.Cyan)
//                .fillMaxWidth(0.5f)
//                .fillMaxHeight(0.5f)
//            ) {
//                Greeting(name = "World!")
//                Greeting(name = "Akshay")
//            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}