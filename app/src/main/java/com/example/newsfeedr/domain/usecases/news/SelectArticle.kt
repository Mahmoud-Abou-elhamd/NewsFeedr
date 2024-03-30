package com.example.newsfeedr.domain.usecases.news

import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.selectArticle(url)
    }

}