package com.example.newsfeedr.domain.repository

import androidx.paging.PagingData
import com.example.newsfeedr.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}