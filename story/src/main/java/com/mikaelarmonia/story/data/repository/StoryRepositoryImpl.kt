package com.mikaelarmonia.story.data.repository

import com.mikaelarmonia.story.data.datasource.StoryLocalDataSource
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.domain.repository.StoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class StoryRepositoryImpl(
    private val localDataSource: StoryLocalDataSource,
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : StoryRepository, CoroutineScope{
    override fun streamStories(): Flow<List<Story>> = localDataSource.streamData()

    override suspend fun storeStories(stories: List<Story>): Result<Unit> =
        withContext(coroutineContext) {
            localDataSource.storeData(stories)
        }
}