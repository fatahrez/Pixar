package com.fatah.pixar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fatah.pixar.feature_search.presentation.ImageDetailScreen
import com.fatah.pixar.feature_search.presentation.ImageList.ImageListScreen
import com.fatah.pixar.feature_search.presentation.profile.ProfileScreen

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

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object SearchScreen: Screen("search_screen")
    object ImageDetailScreen: Screen("image_detail_screen")
    object ProfileScreen: Screen("profile_screen")
}