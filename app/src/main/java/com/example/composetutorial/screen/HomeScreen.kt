package com.example.composetutorial.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.navigation.screens.BottomNavigationScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Home Screen", color = Color.Black, fontSize = 18.sp)
        OutlinedButton(
            onClick = {
                navController.navigate(BottomNavigationScreen.HomeInternalScreen.route)
            },
        ) {
            Text(text = "Go to next screen")
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                navController.navigate(BottomNavigationScreen.AddFriendScreen.route)
            },
        ) {
            Text(text = "Add Friend(Opens New Screen)")
        }


    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}