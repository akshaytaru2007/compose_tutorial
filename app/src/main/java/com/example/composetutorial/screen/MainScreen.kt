package com.example.composetutorial.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.data.model.NavigationDestinationEntry
import com.example.composetutorial.navigation.navgraphs.BottomNavGraph
import com.example.composetutorial.navigation.screens.BottomNavigationScreen
import com.example.composetutorial.utility.AddItem
import com.example.composetutorial.utility.bottomBatVisibilityAsState
import com.example.composetutorial.viewmodel.MainScreenViewModel

private const val TAG = "MainScreen"

private val screen = listOf(
    BottomNavigationScreen.HomeScreen,
    BottomNavigationScreen.AllDrugScreen,
    BottomNavigationScreen.CalculatorScreen,
    BottomNavigationScreen.FriendsScreen,
)

private val screenWithoutBottomBar = listOf(
    BottomNavigationScreen.AddFriendScreen
)


@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel? = hiltViewModel()
) {
    val bottomBarState = navController.bottomBatVisibilityAsState(screenWithoutBottomBar)

    Scaffold(
        topBar = {
            Topbar(
                navController = navController,
                viewModel = viewModel!!
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
private fun Topbar(navController: NavController, viewModel: MainScreenViewModel) {
    val navigationDestinationEntry: NavigationDestinationEntry by navController.destinationEntryAsState(
        screen = screen,
        viewModel = viewModel
    )
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
private fun BottomBar(navController: NavHostController) {

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


//Keeping this logic here, to simplify the understanding
@Composable
fun NavController.destinationEntryAsState(
    screen: List<BottomNavigationScreen>,
    viewModel: MainScreenViewModel
): State<NavigationDestinationEntry> {
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
                currentTitle = viewModel.getDestinationTitle(destination.route),
                showBackButton = viewModel.showBackButton(destination.route, screen)
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
@Preview(showBackground = true)
private fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}
