package com.example.newsfeedr.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun NavGraph(
    startDestination: String
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.HomeScreen.route
        ){
            composable(route = Route.HomeScreen.route) {

            }

            composable(route = Route.FavoriteScreen.route) {

            }
        }
    }
}