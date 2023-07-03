package com.mikaelarmonia.story.ui

import androidx.lifecycle.viewModelScope
import com.mikaelarmonia.story.domain.GetStory
import com.mikaelarmonia.ui.mvi.EurosportTTBaseMviViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

private val stateInitializer: () -> State = { State() }

class StoryViewModel(
    storyId: Long,
    getStory: GetStory
) : EurosportTTBaseMviViewModel<State, Unit>(stateInitializer) {

    init {
        viewModelScope.launch {
            val story = getStory(storyId)
            updateViewState<State> {copy(
                image = story.image,
                sport = story.sport.name,
                title = story.title,
                author = story.author,
                date = story.date,
                content = story.teaser,
            )}
        }
    }
}

data class State(
    val image: String = "",
    val sport: String = "",
    val title: String = "",
    val author: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val content: String = "",
)
