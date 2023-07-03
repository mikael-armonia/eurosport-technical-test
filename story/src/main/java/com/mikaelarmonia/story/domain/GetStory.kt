package com.mikaelarmonia.story.domain

import com.mikaelarmonia.story.domain.repository.StoryRepository

class GetStory(
    private val storyRepository: StoryRepository
) {
    suspend operator fun invoke(id: Long) = storyRepository.getStory(id)
}