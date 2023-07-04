package com.mikaelarmonia.story.data.repository

import com.mikaelarmonia.story.StoryData.stories
import com.mikaelarmonia.story.StoryData.story1
import com.mikaelarmonia.story.data.datasource.StoryLocalDataSource
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

class StoryRepositoryImplTest {

    private lateinit var mockLocalDataSource: StoryLocalDataSource
    private lateinit var storyRepository: StoryRepositoryImpl

    @Before
    fun setup() {
        mockLocalDataSource = mockk(relaxed = true)
        storyRepository = StoryRepositoryImpl(mockLocalDataSource)
    }

    @Test
    fun `test getStory calls localDataSource getData`() = runTest {
        val storyId = 123L
        val story = story1

        coEvery { mockLocalDataSource.getData(storyId) } returns story

        val result = storyRepository.getStory(storyId)

        coVerify { mockLocalDataSource.getData(storyId) }

        assert(result == story)
    }

    @Test
    fun `test streamStories returns flow of stories`() = runTest {
        every { mockLocalDataSource.streamData() } returns flowOf(stories)

        val result = storyRepository.streamStories().first()

        verify { mockLocalDataSource.streamData() }

        assert(result == stories)
    }

    @Test
    fun `test storeStories calls localDataSource storeData`() = runTest {
        val storeResult = Result.success(Unit)

        coEvery { mockLocalDataSource.storeData(stories) } returns storeResult

        val result = storyRepository.storeStories(stories)

        coVerify { mockLocalDataSource.storeData(stories) }

        assert(result == storeResult)
    }
}
