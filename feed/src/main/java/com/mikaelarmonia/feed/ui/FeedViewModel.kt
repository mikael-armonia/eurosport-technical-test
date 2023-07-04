package com.mikaelarmonia.feed.ui

import androidx.lifecycle.viewModelScope
import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.feed.domain.FetchFeedData
import com.mikaelarmonia.feed.domain.StreamFeed
import com.mikaelarmonia.story.ui.screen.StoryScreen
import com.mikaelarmonia.ui.mvi.EurosportTTBaseMviViewModel
import com.mikaelarmonia.ui.screen.repository.NavigatorRepository
import com.mikaelarmonia.video.ui.screen.VideoScreen
import kotlinx.coroutines.launch

internal val stateInitializer: () -> State = { State.Loading }

class FeedViewModel(
    fetchFeedData: FetchFeedData,
    streamFeed: StreamFeed,
    private val navigator: NavigatorRepository
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
        when (intent) {
            is Intent.GoToStory -> navigator.navigateToScreen(
                StoryScreen(intent.storyId)
            )
            is Intent.GoToVideo -> navigator.navigateToScreen(
                VideoScreen(intent.videoId)
            )
        }
    }
}

sealed class State {
    object Loading : State()
    data class Feed(
        val articles: List<Article> = emptyList()
    ) : State()
}

sealed class Intent {
    data class GoToStory(
        val storyId: Long
    ) : Intent()
    data class GoToVideo(
        val videoId: Long
    ) : Intent()
}
