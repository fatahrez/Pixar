package com.fatah.pixar

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fatah.pixar.feature_search.presentation.ImageList.ImageListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            ImageListScreen(navController = navController)
        }

        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object SearchScreen: Screen("search_screen")
}