package com.example.newsfeedr.domain.usecases.news

import com.example.newsfeedr.domain.repository.NewsRepository

class ToggleFavoriteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(isFavorite: Boolean, url: String){
        return newsRepository.toggleFavorite(isFavorite, url)
    }
}