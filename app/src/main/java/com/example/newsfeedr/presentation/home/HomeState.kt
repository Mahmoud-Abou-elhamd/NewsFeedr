package com.example.newsfeedr.presentation.home

import androidx.paging.PagingData
import com.example.newsfeedr.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
