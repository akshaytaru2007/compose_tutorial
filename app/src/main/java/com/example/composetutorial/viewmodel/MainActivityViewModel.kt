package com.example.composetutorial.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetutorial.data.DataStoreRepository
import com.example.composetutorial.navigation.screens.AUTH_GRAPH_ROUTE
import com.example.composetutorial.navigation.screens.MAIN_GRAPH_ROUTE
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

// Abhishek: Move this viewmodel to architecture specific module

class MainActivityViewModel
@Inject
constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {
    private val TAG = "MainActivityViewModel"
    private val _startDestination: MutableState<String> = mutableStateOf(AUTH_GRAPH_ROUTE)
    val startDestination: State<String> = _startDestination

    init {
        Log.d(TAG, "Init called: ")

        viewModelScope.launch {
            dataStoreRepository.isLoggedIn().collect { isLoggedIn ->
                _startDestination.value = if (isLoggedIn) MAIN_GRAPH_ROUTE else AUTH_GRAPH_ROUTE
            }
        }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
        super.onCleared()
    }
}
