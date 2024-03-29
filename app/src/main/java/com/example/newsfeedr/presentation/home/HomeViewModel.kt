package com.example.newsfeedr.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsfeedr.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private var _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is HomeEvent.SearchNews -> {
                searchNews()
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
}