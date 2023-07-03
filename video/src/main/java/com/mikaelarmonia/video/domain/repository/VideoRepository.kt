package com.mikaelarmonia.video.domain.repository

import com.mikaelarmonia.video.data.model.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getVideo(videoId: Long): Video
    fun streamVideos(): Flow<List<Video>>
    suspend fun storeVideos(videos: List<Video>): Result<Unit>
}