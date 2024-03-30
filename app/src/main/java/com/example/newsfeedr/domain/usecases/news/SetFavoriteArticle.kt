package com.example.newsfeedr.domain.usecases.news

import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.repository.NewsRepository

class SetFavoriteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(isFavorite: Boolean, url: String){
        return newsRepository.setFavoriteOrNot(isFavorite, url)
    }
}