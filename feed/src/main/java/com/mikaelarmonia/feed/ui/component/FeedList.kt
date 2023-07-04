package com.mikaelarmonia.feed.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.feed.ui.Intent

@Composable
fun FeedList(
    modifier: Modifier = Modifier,
    feed: List<Article>,
    onItemClick: (Article) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        items(feed) { article ->
            FeedCard(
                modifier = Modifier.padding(bottom = 16.dp),
                article = article,
                onItemClick = onItemClick
            )
        }
    }
}