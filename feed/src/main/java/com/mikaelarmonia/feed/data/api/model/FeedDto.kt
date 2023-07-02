package com.mikaelarmonia.feed.data.api.model

import com.mikaelarmonia.story.data.api.model.StoryDto
import com.mikaelarmonia.video.data.api.model.VideoDto

data class FeedDto(
    val videos: List<VideoDto>,
    val stories: List<StoryDto>,
)
