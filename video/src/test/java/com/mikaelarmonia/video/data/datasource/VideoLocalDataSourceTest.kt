package com.mikaelarmonia.video.data.datasource

import com.mikaelarmonia.video.VideoData.video1
import com.mikaelarmonia.video.VideoEntityData.videoEntity1
import com.mikaelarmonia.video.data.db.dao.VideoDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class VideoLocalDataSourceTest {

    private lateinit var mockVideoDao: VideoDao
    private lateinit var videoLocalDataSource: VideoLocalDataSource

    @Before
    fun setup() {
        mockVideoDao = mockk(relaxed = true)

        videoLocalDataSource = VideoLocalDataSource(mockVideoDao)
    }

    @Test
    fun `test getVideo calls videoDao get`() = runTest {
        val videoId = 123L

        coEvery { mockVideoDao.get(123L) } returns videoEntity1
        val result = videoLocalDataSource.get(videoId)

        coVerify { mockVideoDao.get(videoId) }

        assert(result == video1)
    }

    @Test
    fun `test streamData returns flow of videos`() = runTest {
        val videoEntities = listOf(videoEntity1)
        val videos = listOf(video1)

        every { mockVideoDao.streamAll() } returns flowOf(videoEntities)

        val result = videoLocalDataSource.streamData().first()

        verify { mockVideoDao.streamAll() }

        assert(result == videos)
    }

    @Test
    fun `test storeData calls videoDao deleteAll and insertAll`() = runTest {
        val videos = listOf(video1)
        val videoEntities = listOf(videoEntity1)

        coEvery { mockVideoDao.deleteAll() } just runs
        coEvery { mockVideoDao.insertAll(*anyVararg()) } just runs

        val result = videoLocalDataSource.storeData(videos)

        coVerify { mockVideoDao.deleteAll() }
        coVerify { mockVideoDao.insertAll(*videoEntities.toTypedArray()) }

        assert(result.isSuccess)
    }
}