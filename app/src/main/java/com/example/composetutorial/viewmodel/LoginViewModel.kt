package com.example.composetutorial.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetutorial.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {
    private val TAG = "LoginViewModel"
    init {
        Log.d(TAG, "Init called ")
    }
    
    fun saveLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveLoginState(true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}