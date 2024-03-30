package com.example.newsfeedr.domain.usecases.news

import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}