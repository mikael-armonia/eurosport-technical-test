package com.mikaelarmonia.feed.domain

import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.story.domain.repository.StoryRepository
import com.mikaelarmonia.video.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class StreamFeed(
    private val storyRepository: StoryRepository,
    private val videoRepository: VideoRepository,
) {
    operator fun invoke(): Flow<List<Article>> = combine(
        storyRepository.streamStories(),
        videoRepository.streamVideos(),
    ) { stories, videos ->
        val sortedStories = stories.sortedByDescending { it.date.nano }
        val sortedVideos = videos.sortedByDescending { it.date.nano }

        sortedStories.mergeAlternating(sortedVideos)
    }
}

private fun <T> List<T>.mergeAlternating(other: List<T>): List<T> {
    val result = flatMapIndexed { i, value1 ->
        other.getOrNull(i)?.let { value2 ->
            listOf(value1, value2)
        } ?: listOf(value1)
    }

    if (result.size < other.size) {
        result.toMutableList().addAll(other.slice(result.size..other.lastIndex))
    }

    return result
}
