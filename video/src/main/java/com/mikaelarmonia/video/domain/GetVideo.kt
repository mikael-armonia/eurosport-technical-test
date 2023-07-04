package com.mikaelarmonia.video.domain

import com.mikaelarmonia.video.domain.repository.VideoRepository

internal class GetVideo(
    private val videoRepository: VideoRepository
) {
    suspend operator fun invoke(videoId: Long) = videoRepository.getVideo(videoId)
}
