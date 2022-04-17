package com.example.composetutorial.screen

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.data.model.NavigationDestinationEntry
import com.example.composetutorial.navigation.screens.BottomNavigationScreen
import com.example.composetutorial.navigation.navgraphs.BottomNavGraph

private const val TAG = "MainScreen"

// Abhishek: Provide private access specifier for composables not used outside.
val screen = listOf(
    BottomNavigationScreen.HomeScreen,
    BottomNavigationScreen.AllDrugScreen,
    BottomNavigationScreen.CalculatorScreen,
    BottomNavigationScreen.FriendsScreen,
)

val screenWithoutBottomBar = listOf(
    BottomNavigationScreen.AddFriendScreen
)


@Composable
fun MainScreen(navController: NavHostController) {
    val bottomBarState = navController.bottomBatVisibilityAsState()

    Scaffold(
        topBar = {
            Topbar(
                navController = navController
            )
        },
        bottomBar = {
            if (bottomBarState.value) {
                BottomBar(navController = navController)
            } else Unit
        }

    ) {
        BottomNavGraph(navController = navController)

    }
}

@Composable
fun Topbar(navController: NavController) {
    val navigationDestinationEntry: NavigationDestinationEntry by navController.destinationEntryAsState()
    TopAppBar(
        title = { Text(text = navigationDestinationEntry.currentTitle) },
        navigationIcon = {

            if (navigationDestinationEntry.showBackButton) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            } else null
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screen.forEach {
            AddItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController,
            )
        }
    }
}

// Abhishek: Do composables fuctions return? Use extension fucntions logic in a separate file. Create separate class for reusable composables.
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

// Abhishek: Do composables fuctions return? Use extension fucntions logic in a separate file. Create separate class for reusable composables.
@Composable
fun NavController.destinationEntryAsState(): State<NavigationDestinationEntry> {
    val navDestinationEntry = remember {
        mutableStateOf(
            NavigationDestinationEntry(
                currentTitle = "Hone",
                showBackButton = false
            )
        )
    }

    DisposableEffect(this) {
        val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
            navDestinationEntry.value = NavigationDestinationEntry(
                currentTitle = getDestinationTitle(destination.route),
                showBackButton = showBackButton(destination.route)
            )
        }
        addOnDestinationChangedListener(callback)
        onDispose {
            removeOnDestinationChangedListener(callback)
        }
    }

    return navDestinationEntry
}


@Composable
fun NavController.bottomBatVisibilityAsState(): State<Boolean> {
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

// Abhishek: showBackButton(), getDestinationTitle() - try to keep these methods on viewmodel and separate UI logic from business logic. 
fun showBackButton(route: String?): Boolean =
    screen.firstOrNull() { it.route == route }?.route != route

// Abhishek: showBackButton(), getDestinationTitle() - try to keep these methods on viewmodel and separate UI logic from business logic.
// Abhishek: Create separate state class in viewmodel specific to it and access via objects
fun getDestinationTitle(route: String?): String {
    val screen = listOf(
        BottomNavigationScreen.HomeScreen,
        BottomNavigationScreen.AllDrugScreen,
        BottomNavigationScreen.CalculatorScreen,
        BottomNavigationScreen.FriendsScreen,
        BottomNavigationScreen.HomeInternalScreen,
        BottomNavigationScreen.AddFriendScreen,
    )
    return screen.firstOrNull { it.route == route }?.title ?: ""
}


@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}
