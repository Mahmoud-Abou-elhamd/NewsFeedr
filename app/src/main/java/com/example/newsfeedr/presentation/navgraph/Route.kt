package com.example.newsfeedr.presentation.navgraph


sealed class Route(
    val route: String,
) {
    object HomeScreen : Route(route = "homeScreen")
    object FavoriteScreen : Route(route = "favoriteScreen")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigatorScreen : Route(route = "newsNavigator")
}