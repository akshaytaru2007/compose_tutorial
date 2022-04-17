package com.example.composetutorial.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.navigation.screens.BottomNavigationScreen
import com.example.composetutorial.ui.theme.CustomThemeManager
import com.example.composetutorial.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .background(color = CustomThemeManager.colors.backgroundColor)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Home Screen", color = CustomThemeManager.colors.textColor, fontSize = 18.sp)
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
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = {
//                CustomThemeManager.customTheme =
//                    if (CustomThemeManager.customTheme == ThemeMode.DARK) {
//                        ThemeMode.LIGHT
//                    } else {
//                        ThemeMode.DARK
//                    }

                viewModel.toggleThemeMode()
            },
        ) {
            Text(text = "Change Theme Mode ")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Current Theme is  ${
                if (CustomThemeManager.isSystemInDarkMode()) {
                    "Dark"
                } else {
                    "Light"
                }
            }"
        )

    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}