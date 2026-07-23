// ui/screens/MainTabScreen.kt
package com.example.dalag.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dalag.ui.viewmodel.UserViewModel

@Composable
fun MainTabScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text(if (userViewModel.currentLanguage.value == "so") "Bogga Hore" else "Home") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Storefront, contentDescription = "Markets") },
                    label = { Text(if (userViewModel.currentLanguage.value == "so") "Suuqyada" else "Markets") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                    label = { Text(if (userViewModel.currentLanguage.value == "so") "Ku Dar" else "Add") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text(if (userViewModel.currentLanguage.value == "so") "Dookhyada" else "Settings") },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> HomeScreen(userViewModel = userViewModel)
                1 -> MarketsScreen(userViewModel = userViewModel)
                2 -> AddPriceScreen(userViewModel = userViewModel)
                3 -> SettingsScreen(userViewModel = userViewModel)
            }
        }
    }
}
