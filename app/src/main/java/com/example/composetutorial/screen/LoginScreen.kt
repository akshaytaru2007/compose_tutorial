package com.example.composetutorial.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.navigation.screens.MAIN_GRAPH_ROUTE
import com.example.composetutorial.navigation.screens.Screen
import com.example.composetutorial.viewmodel.LoginViewModel


private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel? = hiltViewModel()) {

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Abhishek: Use the default text from strings resource
        Text("Login Screen", color = Color.Black, fontSize = 18.sp)
        OutlinedButton(onClick = {
            Log.d(TAG, "LoginScreen: Button clicked")
            viewModel?.saveLogin()
            navController.navigate(MAIN_GRAPH_ROUTE)
            navController.popBackStack()


        }) {
            // Abhishek: Use the default text from strings resource
            Text(text = "Login")

        }
        Spacer(modifier = Modifier.height(10.dp))
        ClickableText(
            // Abhishek: Use the default text from strings resource
            text = AnnotatedString(text = "Terms And Condition"),
            onClick = {
                navController.navigate(Screen.TermsAndConditionScreen.route)
            }
        )


    }
}

// Abhishek: Preview method can be made private
@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController(), null)
}
