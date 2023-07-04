package com.mikaelarmonia.story.data.datasource

import com.mikaelarmonia.story.StoryData.story1
import com.mikaelarmonia.story.StoryEntityData.storyEntity1
import com.mikaelarmonia.story.data.db.dao.StoryDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class StoryLocalDataSourceTest {

    private lateinit var mockStoryDao: StoryDao
    private lateinit var storyLocalDataSource: StoryLocalDataSource

    @Before
    fun setup() {
        mockStoryDao = mockk(relaxed = true)
        storyLocalDataSource = StoryLocalDataSource(mockStoryDao)
    }

    @Test
    fun `test getData calls storyDao get`() = runTest {
        val storyId = 123L
        val storyEntity = storyEntity1
        val story = story1

        coEvery { mockStoryDao.get(storyId) } returns storyEntity

        val result = storyLocalDataSource.getData(storyId)

        coVerify { mockStoryDao.get(storyId) }

        assertEquals(result, story)
    }

    @Test
    fun `test streamData returns flow of stories`() = runTest {
        val storyEntities = listOf(storyEntity1)
        val stories = listOf(story1)

        every { mockStoryDao.streamAll() } returns flowOf(storyEntities)

        val result = storyLocalDataSource.streamData().first()

        verify { mockStoryDao.streamAll() }

        assert(result == stories)
    }

    @Test
    fun `test storeData calls storyDao deleteAll and insertAll`() = runTest {
        val stories = listOf(story1)
        val storyEntities = listOf(storyEntity1)

        coEvery { mockStoryDao.deleteAll() } just runs
        coEvery { mockStoryDao.insertAll(*anyVararg()) } just runs

        val result = storyLocalDataSource.storeData(stories)

        coVerify { mockStoryDao.deleteAll() }
        coVerify { mockStoryDao.insertAll(*storyEntities.toTypedArray()) }

        assert(result.isSuccess)
    }
}