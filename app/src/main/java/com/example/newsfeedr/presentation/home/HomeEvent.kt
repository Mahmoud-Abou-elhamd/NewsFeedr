package com.example.newsfeedr.presentation.home

sealed class HomeEvent {
    data class UpdateSearchQuery(val searchQuery: String) : HomeEvent()

    object SearchNews : HomeEvent()
}
