package com.mikaelarmonia.feed.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.feed.data.api.FeedService
import com.mikaelarmonia.feed.domain.repository.FeedRepository
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.data.model.toStory
import com.mikaelarmonia.video.data.model.Video
import com.mikaelarmonia.video.data.model.toVideo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class FeedRepositoryImpl(
    private val feedService: FeedService,
    private val storyDataSource: DataSource<Story>,
    private val videoDataSource: DataSource<Video>,
    override val coroutineContext: CoroutineContext = Dispatchers.IO,
) : FeedRepository, CoroutineScope {
    override suspend fun fetchData(): Result<Unit> = withContext(coroutineContext) {
        when (val feedResult = feedService.getFeed()) {
            is NetworkResponse.Success -> with(feedResult.body) {
                storyDataSource.storeData(stories.map { it.toStory() })
                videoDataSource.storeData(videos.map { it.toVideo() })
                Result.success(Unit)
            }
            is NetworkResponse.Error ->
                Result.failure(feedResult.error ?: Error("Unknown error"))
        }
    }
}