package com.example.composetutorial.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composetutorial.navigation.Screen
import com.example.composetutorial.viewmodel.LoginViewModel


private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel =  hiltViewModel()) {



    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Login Screen", color = Color.Black, fontSize = 18.sp)
        OutlinedButton(onClick = {
            Log.d(TAG, "LoginScreen: Button clicked")
            viewModel.saveLogin()
            navController.popBackStack()
            navController.navigate(Screen.HomeScreen.route)


        }) {
            Text(text = "Login")

        }

    }
}

//@Composable
//@Preview(showBackground = true)
//fun LoginScreenPreview() {
//    LoginScreen()
//}