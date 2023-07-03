package com.mikaelarmonia.video.domain.repository

internal class GetVideo(
    private val videoRepository: VideoRepository
) {
    suspend operator fun invoke(videoId: Long) = videoRepository.getVideo(videoId)
}
