package com.example.newsfeedr.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsfeedr.presentation.Dimens.MediumPadding1
import com.example.newsfeedr.presentation.common.ArticlesList
import com.example.newsfeedr.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: HomeState,
    event:(HomeEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(HomeEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(HomeEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = {

                }
            )
        }
    }
}