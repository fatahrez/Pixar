package com.fatah.pixar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fatah.pixar.feature_search.presentation.ImageDetailScreen
import com.fatah.pixar.feature_search.presentation.ImageList.ImageListScreen

@ExperimentalFoundationApi
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

        composable(route = Screen.ImageDetailScreen.route + "/{imageId}") {
            ImageDetailScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object SearchScreen: Screen("search_screen")
    object ImageDetailScreen: Screen("image_detail_screen")
}