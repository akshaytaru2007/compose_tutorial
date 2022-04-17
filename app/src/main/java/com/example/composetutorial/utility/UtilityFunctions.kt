package com.example.composetutorial.utility

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.composetutorial.navigation.screens.BottomNavigationScreen

private const val TAG = "UtilityFunctions"

@Composable
fun RowScope.AddItem(
    screen: BottomNavigationScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        label = { Text(text = screen.title) },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title
            )
        },
        selectedContentColor = screen.selectedContentColor,
        unselectedContentColor = screen.unselectedContentColor,
        onClick = {
            navController.navigate(screen.route) {
                Log.d(TAG, "AddItem: Navigating to ${screen.route}")
                screen.title
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun NavController.bottomBatVisibilityAsState(screenWithoutBottomBar: List<BottomNavigationScreen>): State<Boolean> {
    val bottomBarVisible = remember {
        mutableStateOf(true)
    }

    DisposableEffect(this) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            bottomBarVisible.value = screenWithoutBottomBar.firstOrNull {
                it.route == destination.route
            }?.route != destination.route
        }
        addOnDestinationChangedListener(callback)
        onDispose {
            removeOnDestinationChangedListener(callback)
        }
    }

    return bottomBarVisible
}
