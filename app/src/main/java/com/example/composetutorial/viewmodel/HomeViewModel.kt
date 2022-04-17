package com.example.composetutorial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetutorial.data.DataStoreRepository
import com.example.composetutorial.ui.theme.CustomThemeManager
import com.example.composetutorial.ui.theme.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) :
    ViewModel() {
    private var themeMode: ThemeMode = ThemeMode.LIGHT

    init {
        viewModelScope.launch {
            dataStoreRepository.getThemeMode().collect {
                themeMode = it
                CustomThemeManager.customTheme = it
            }
        }
    }

    fun toggleThemeMode() {
        viewModelScope.launch(Dispatchers.IO) {
            val mode = if (themeMode == ThemeMode.LIGHT) {
                ThemeMode.DARK
            } else {
                ThemeMode.LIGHT
            }
            dataStoreRepository.saveThemeMode(themeMode = mode)
        }
    }
}