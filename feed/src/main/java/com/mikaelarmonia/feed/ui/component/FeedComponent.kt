package com.mikaelarmonia.feed.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mikaelarmonia.feed.R
import com.mikaelarmonia.feed.ui.FeedViewModel
import com.mikaelarmonia.feed.ui.State
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedComponent(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel()
) {
    val state by viewModel.viewStateFlow.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            FeedTopBar(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.top_bar_title)
            )
        }
    ) {
        when (val currentState = state) {
            is State.Feed -> {
                if (currentState.articles.isNotEmpty()) {
                    FeedList(
                        modifier = modifier.padding(it),
                        feed = currentState.articles
                    )
                } else {
                    FeedError(modifier = modifier)
                }
            }
            State.Loading -> FeedLoading(modifier = modifier)
        }
    }
}
