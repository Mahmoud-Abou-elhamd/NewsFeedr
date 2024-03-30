package com.example.newsfeedr.domain.usecases.news

import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}