package com.example.newsfeedr.presentation.home

import com.example.newsfeedr.domain.model.Article

sealed class HomeEvent {
    data class UpdateSearchQuery(val searchQuery: String) : HomeEvent()
    object SearchNews : HomeEvent()
    data class UpsertDeleteArticle(val article: Article) : HomeEvent()
    object RemoveSideEffect : HomeEvent()
}
