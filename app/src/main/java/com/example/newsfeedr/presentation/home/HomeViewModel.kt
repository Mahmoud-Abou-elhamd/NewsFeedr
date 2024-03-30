package com.example.newsfeedr.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private var _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state
    var sideEffect by mutableStateOf<String?>(null)
        private set

    init {
        searchNews()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is HomeEvent.SearchNews -> {
                searchNews()
            }

            is HomeEvent.UpsertDeleteArticle -> {
                viewModelScope.launch{
                    val article = newsUseCases.selectArticle(event.article.url)
                    //val isFavorite = article?.isFavorite ?: true
                    //_state.value.article.isFavorite = (event.article.isFavorite)
                    if(article == null){
                        setFavoriteArticle(true, event.article.url)
                        upsertArticle(event.article)
                        _state.value.article.isFavorite = false
                    }else{
                        setFavoriteArticle(false, event.article.url)
                        deleteArticle(event.article)
                        _state.value.article.isFavorite = true
                    }
                }
            }

            is HomeEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            searchQuery = _state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = "Article Deleted"
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = "Article Saved"
    }

    private suspend fun setFavoriteArticle(isFavorite: Boolean, url: String) {
        newsUseCases.setFavoriteArticle(isFavorite, url)
    }
}