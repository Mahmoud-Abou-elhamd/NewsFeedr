package com.example.newsfeedr.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsfeedr.R
import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.presentation.Dimens.MediumPadding
import com.example.newsfeedr.presentation.common.ArticlesList

@Composable
fun FavoriteScreen(
    state: FavoriteState,
    navigateToDetails: (Article) -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding, start = MediumPadding, end = MediumPadding)
    ) {
        Text(
            text = "Favorite",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        ArticlesList(articles = state.articles, onClick = { navigateToDetails(it) })
    }
}