package com.mikaelarmonia.feed.ui

import androidx.lifecycle.viewModelScope
import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.feed.domain.FetchFeedData
import com.mikaelarmonia.feed.domain.StreamFeed
import com.mikaelarmonia.ui.mvi.EurosportTTBaseMviViewModel
import kotlinx.coroutines.launch

private val stateInitializer: () -> State = { State.Loading }

class FeedViewModel(
    private val fetchFeedData: FetchFeedData,
    private val streamFeed: StreamFeed,
) : EurosportTTBaseMviViewModel<State, Intent>(stateInitializer) {

    init {
        viewModelScope.launch {
            fetchFeedData()
        }
        viewModelScope.launch {
            streamFeed().collect { articles ->
                updateViewState<State.Feed> { copy(
                    articles = articles
                )}
            }
        }
    }

    override fun dispatchIntent(intent: Intent) {
        TODO("Not yet implemented")
    }
}

sealed class State {
    object Loading : State()
    data class Feed(
        val articles: List<Article> = emptyList()
    ) : State()
}

sealed class Intent
