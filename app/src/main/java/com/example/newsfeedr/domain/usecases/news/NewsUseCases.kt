package com.example.newsfeedr.domain.usecases.news

data class NewsUseCases(
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle,
    val setFavoriteArticle: SetFavoriteArticle
)
