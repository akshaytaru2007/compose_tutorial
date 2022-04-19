package com.example.composetutorial.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ContactUsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Contact Us", color = Color.Black, fontSize = 20.sp)
    }
}

@Composable
@Preview(showBackground = true)
private fun ContactUsScreenPreview() {
    ContactUsScreen(navController = rememberNavController())
}