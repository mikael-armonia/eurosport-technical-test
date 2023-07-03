package com.mikaelarmonia.video.ui

import androidx.lifecycle.viewModelScope
import com.mikaelarmonia.ui.mvi.EurosportTTBaseMviViewModel
import com.mikaelarmonia.video.domain.repository.GetVideo
import kotlinx.coroutines.launch

private val stateInitializer: () -> State = { State() }

internal class VideoViewModel(
    videoId: Long,
    getVideo: GetVideo,
) : EurosportTTBaseMviViewModel<State, Unit>(stateInitializer) {

    init {
        viewModelScope.launch {
            val video = getVideo(videoId)
            updateViewState<State> { copy(
                url = video.url
            )}
        }
    }
}

data class State(
    val url: String = ""
)
