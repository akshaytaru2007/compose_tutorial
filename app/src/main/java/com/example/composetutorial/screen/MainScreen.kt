package com.example.composetutorial.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.R
import com.example.composetutorial.data.model.NavigationDestinationEntry
import com.example.composetutorial.navigation.navgraphs.BottomNavGraph
import com.example.composetutorial.navigation.screens.BottomNavigationScreen
import com.example.composetutorial.ui.theme.CustomThemeManager
import com.example.composetutorial.utility.AddItem
import com.example.composetutorial.utility.bottomBatVisibilityAsState
import com.example.composetutorial.viewmodel.MainScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private const val TAG = "MainScreen"

private val screen = listOf(
    BottomNavigationScreen.HomeScreen,
    BottomNavigationScreen.AllDrugScreen,
    BottomNavigationScreen.CalculatorScreen,
    BottomNavigationScreen.FriendsScreen,
)

private val screenWithoutBottomBar = listOf(
    BottomNavigationScreen.AddFriendScreen,
    BottomNavigationScreen.Settings,
    BottomNavigationScreen.AboutUs,
    BottomNavigationScreen.ContactUs,
)


@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainScreenViewModel? = hiltViewModel()
) {
    val bottomBarState = navController.bottomBatVisibilityAsState(screenWithoutBottomBar)
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Topbar(
                navController = navController,
                viewModel = viewModel!!,
                scaffoldState = scaffoldState,
                scope = coroutineScope
            )
        },
        drawerContent = {
            Drawer(
                scope = coroutineScope,
                scaffoldState = scaffoldState,
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
private fun Topbar(
    navController: NavController,
    viewModel: MainScreenViewModel,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
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
            } else {

                IconButton(onClick = {
                    scope.launch { scaffoldState.drawerState.open() }

                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }

            }
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
private fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    val list = listOf(
        BottomNavigationScreen.Settings,
        BottomNavigationScreen.AboutUs,
        BottomNavigationScreen.ContactUs,
    )
    Column(modifier = Modifier.background(color = CustomThemeManager.colors.friendsColor)) {

        //TODO: Could be replaced with Logged In User's image and Name
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(10.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        list.forEach { navDrawerItem ->

            DrawerItem(
                item = navDrawerItem,
                selected = currentRoute == navDrawerItem.route,
                onItemClick = {
                    Log.d(TAG, "Drawer: AK:")
                    navController.navigate(navDrawerItem.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route = route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    //Close Drawer
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }

                })

        }

        Text(
            text = "Developed by Akshay",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun DrawerItem(
    item: BottomNavigationScreen,
    selected: Boolean,
    onItemClick: (BottomNavigationScreen) -> Unit
) {
    val backGround =
        if (selected) R.color.design_default_color_primary_dark
        else R.color.design_default_color_primary

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .background(colorResource(id = backGround))
            .padding(start = 10.dp)
    ) {
        Image(
            imageVector = item.icon,
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.White),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun DrawerPreview() {

    Drawer(
        scope = rememberCoroutineScope(), scaffoldState = rememberScaffoldState(
            rememberDrawerState(
                initialValue = DrawerValue.Closed
            )
        ), navController = rememberNavController()
    )
}

@Composable
@Preview(showBackground = true)
private fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}
