// ui/viewmodel/UserViewModel.kt
package com.example.dalag.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    // Magaca qofka
    var userName = mutableStateOf("")
        private set

    // Luuqada hadda (en ama so)
    var currentLanguage = mutableStateOf("en")
        private set

    fun setUserName(name: String) {
        userName.value = name
    }

    fun toggleLanguage() {
        currentLanguage.value = if (currentLanguage.value == "en") "so" else "en"
    }

    // Waxaad ku dari kartaa xog kale sida dark mode, iwm.
}