package com.example.newsfeedr.domain.repository

import androidx.paging.PagingData
import com.example.newsfeedr.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles(): Flow<List<Article>>

    suspend fun selectArticle(url: String): Article?

    suspend fun setFavoriteOrNot(isFavorite: Boolean, url: String)
}