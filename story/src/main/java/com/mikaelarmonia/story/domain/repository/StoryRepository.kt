package com.mikaelarmonia.story.domain.repository

import com.mikaelarmonia.story.data.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    fun streamStories(): Flow<List<Story>>
    suspend fun storeStories(stories: List<Story>): Result<Unit>
}