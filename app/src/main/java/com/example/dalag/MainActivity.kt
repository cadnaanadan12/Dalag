package com.example.dalag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dalag.ui.screens.LoginScreen
import com.example.dalag.ui.screens.SplashScreen
import com.example.dalag.ui.theme.DalagTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DalagTheme {
                DalagApp()
            }
        }
    }
}

@Composable
fun DalagApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
            ) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        }
        composable("register") {
            // Placeholder for Registration Screen
            Scaffold { padding ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Registration Screen Placeholder")
                }
            }
        }
        composable("home") {
            // Placeholder for Home/Main Screen
            Scaffold { padding ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Home Screen Placeholder")
                }
            }
        }
    }
}
