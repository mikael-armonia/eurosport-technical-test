package com.mikaelarmonia.video.data.repository

import com.mikaelarmonia.video.VideoData.video1
import com.mikaelarmonia.video.VideoData.video2
import com.mikaelarmonia.video.data.datasource.VideoLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VideoRepositoryImplTest {

    private lateinit var mockLocalDataSource: VideoLocalDataSource
    private lateinit var videoRepository: VideoRepositoryImpl

    @Before
    fun setup() {
        mockLocalDataSource = mockk(relaxed = true)
        videoRepository = VideoRepositoryImpl(mockLocalDataSource)
    }

    @Test
    fun `test getVideo calls localDataSource get`() = runTest {
        val videoId = 123L
        val video = video1

        coEvery { mockLocalDataSource.get(videoId) } returns video

        val result = videoRepository.getVideo(videoId)

        coVerify { mockLocalDataSource.get(videoId) }

        assert(result == video)
    }

    @Test
    fun `test streamVideos returns flow of videos`() = runTest {
        val videos = listOf(video1, video2)

        every { mockLocalDataSource.streamData() } returns flowOf(videos)

        val result = videoRepository.streamVideos().first()

        verify { mockLocalDataSource.streamData() }

        assert(result == videos)
    }

    @Test
    fun `test storeVideos calls localDataSource storeData`() = runTest {
        val videos = listOf(video1, video2)
        val storeResult = Result.success(Unit)

        coEvery { mockLocalDataSource.storeData(videos) } returns storeResult

        val result = videoRepository.storeVideos(videos)

        coVerify { mockLocalDataSource.storeData(videos) }

        assert(result == storeResult)
    }
}
