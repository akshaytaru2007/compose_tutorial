package com.example.composetutorial.viewmodel

import androidx.lifecycle.ViewModel
import com.example.composetutorial.data.DataStoreRepository
import com.example.composetutorial.navigation.screens.BottomNavigationScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {


    fun getDestinationTitle(route: String?): String {
        val screen = listOf(
            BottomNavigationScreen.HomeScreen,
            BottomNavigationScreen.AllDrugScreen,
            BottomNavigationScreen.CalculatorScreen,
            BottomNavigationScreen.FriendsScreen,
            BottomNavigationScreen.HomeInternalScreen,
            BottomNavigationScreen.AddFriendScreen,
            BottomNavigationScreen.Settings,
            BottomNavigationScreen.ContactUs,
            BottomNavigationScreen.AboutUs,
        )
        return screen.firstOrNull { it.route == route }?.title ?: ""
    }

    fun showBackButton(route: String?, screen: List<BottomNavigationScreen>): Boolean =
        screen.firstOrNull() { it.route == route }?.route != route
}