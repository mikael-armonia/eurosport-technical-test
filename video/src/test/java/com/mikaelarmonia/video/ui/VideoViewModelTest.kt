package com.mikaelarmonia.video.ui

import com.mikaelarmonia.video.VideoData.video1
import com.mikaelarmonia.video.domain.GetVideo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class VideoViewModelTest {

    private lateinit var mockGetVideo: GetVideo
    private lateinit var videoViewModel: VideoViewModel

    private lateinit var testScope: TestScope

    @Before
    fun setup() {
        mockGetVideo = mockk()
        coEvery { mockGetVideo(123L) } returns video1

        val testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)

        videoViewModel = VideoViewModel(123L, mockGetVideo)
    }

    @Test
    fun `test initialization calls getVideo and updates view state`() = testScope.runTest {
        val videoId = 123L
        val state = State(
            url = "https://url.url"
        )

        val viewState = videoViewModel.viewState

        coVerify { mockGetVideo(videoId) }
        assertEquals(viewState, state)
    }
}