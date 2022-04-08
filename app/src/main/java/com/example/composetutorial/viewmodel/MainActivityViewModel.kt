package com.example.composetutorial.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetutorial.data.DataStoreRepository
import com.example.composetutorial.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository): ViewModel() {
    private val TAG = "MainActivityViewModel"
    private val _startDestination: MutableState<String> = mutableStateOf(Screen.LoginScreen.route)
    val startDestination: State<String> = _startDestination
    init {
        Log.d(TAG, "Init called: ")

        viewModelScope.launch {
            dataStoreRepository.isLoggedIn().collect { isLoggedIn ->
                _startDestination.value = if (isLoggedIn) Screen.HomeScreen.route else Screen.LoginScreen.route
            }
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
        super.onCleared()
    }
}