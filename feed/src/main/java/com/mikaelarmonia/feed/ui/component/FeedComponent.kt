package com.mikaelarmonia.feed.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mikaelarmonia.feed.R
import com.mikaelarmonia.feed.ui.FeedViewModel
import com.mikaelarmonia.feed.ui.State
import com.mikaelarmonia.ui.toolbar.TopBarState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedComponent(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel(),
    topBarConfigurator: @Composable (TopBarState) -> Unit
) {
    val state by viewModel.viewStateFlow.collectAsState()
    topBarConfigurator(
        TopBarState(
            title = stringResource(id = R.string.top_bar_title),
            backgroundColor = MaterialTheme.colorScheme.primary
        )
    )

    when (val currentState = state) {
        is State.Feed -> {
            if (currentState.articles.isNotEmpty()) {
                FeedList(
                    modifier = modifier,
                    feed = currentState.articles
                )
            } else {
                FeedError(modifier = modifier)
            }
        }
        State.Loading -> FeedLoading(modifier = modifier)
    }
}
