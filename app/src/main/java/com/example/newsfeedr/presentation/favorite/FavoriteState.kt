package com.example.newsfeedr.presentation.favorite

import com.example.newsfeedr.domain.model.Article

data class FavoriteState(
    val articles: List<Article> = emptyList()
)
