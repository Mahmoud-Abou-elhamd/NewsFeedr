package com.example.newsfeedr.domain.usecases.news

import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}