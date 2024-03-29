package com.example.newsfeedr.data.remote.dto

import com.example.newsfeedr.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)