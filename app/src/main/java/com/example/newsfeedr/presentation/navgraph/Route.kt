package com.example.newsfeedr.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val args: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")
    object FavoriteScreen : Route(route = "favoriteScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
}