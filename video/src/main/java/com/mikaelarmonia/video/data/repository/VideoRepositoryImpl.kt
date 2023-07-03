package com.mikaelarmonia.video.data.repository

import com.mikaelarmonia.video.data.datasource.VideoLocalDataSource
import com.mikaelarmonia.video.data.model.Video
import com.mikaelarmonia.video.domain.repository.VideoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class VideoRepositoryImpl(
    private val localDataSource: VideoLocalDataSource,
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : VideoRepository, CoroutineScope{
    override suspend fun getVideo(videoId: Long): Video = localDataSource.get(videoId)

    override fun streamVideos(): Flow<List<Video>> = localDataSource.streamData()

    override suspend fun storeVideos(videos: List<Video>): Result<Unit> =
        withContext(coroutineContext) {
            localDataSource.storeData(videos)
        }
}
