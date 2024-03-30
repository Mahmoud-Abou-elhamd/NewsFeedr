package com.example.newsfeedr.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.newsfeedr.R
import com.example.newsfeedr.domain.model.Article
import com.example.newsfeedr.domain.model.Source
import com.example.newsfeedr.presentation.Dimens.ArticleCardSize
import com.example.newsfeedr.presentation.Dimens.ExtraSmallPadding
import com.example.newsfeedr.presentation.Dimens.SmallPadding
import com.example.newsfeedr.presentation.Dimens.SmallIconSize
import com.example.newsfeedr.ui.theme.NewsFeedrTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null,
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },
        verticalAlignment = Alignment.Top
        ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            loading = {CircularProgressIndicator(Modifier.padding(all = 20.dp))},
            error = { it.let { R.drawable.ic_time } },
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = SmallPadding)
                .wrapContentHeight()
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = colorResource(id = R.color.text_title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.author ?: article.source.name,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.body),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Text(
                text = article.description,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light),
                color = colorResource(id = R.color.body),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Text(
                text = article.url,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light),
                color = colorResource(id = R.color.body),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = if(article.isFavorite) painterResource(id = R.drawable.ic_filled_favorite) else painterResource(id = R.drawable.ic_favorite),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsFeedrTheme(dynamicColor = false) {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "Her train broke down. Her phone died. And then she met her Saver in a",
                url = "",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            ),
            onClick = {}
        )
    }
}