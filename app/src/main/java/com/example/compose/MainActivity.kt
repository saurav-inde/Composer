package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.presentation.theme.ComposeTheme
import com.example.compose.presentation.screens.NotesDetailScreen
import com.example.compose.presentation.screens.NotesHomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            ComposeTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                )
                {
                    composable("home") { NotesHomeScreen(navController) }
                    composable("details") {
                        NotesDetailScreen(navController)
                    }
                }
            }
        }

    }


}