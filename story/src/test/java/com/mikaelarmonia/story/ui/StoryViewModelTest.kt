package com.mikaelarmonia.story.ui

import com.mikaelarmonia.story.StoryData.story1
import com.mikaelarmonia.story.domain.GetStory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class StoryViewModelTest {

    private lateinit var mockGetStory: GetStory
    private lateinit var storyViewModel: StoryViewModel

    private lateinit var testScope : TestScope

    @Before
    fun setup() {
        mockGetStory = mockk()
        coEvery { mockGetStory(123L) } returns story1

        val testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)

        storyViewModel = StoryViewModel(123L, mockGetStory)
    }

    @Test
    fun `test initialization calls getStory and updates view state`() = testScope.runTest {
        val storyId = 123L
        val story = story1
        val state = State(
            image = story.image,
            sport = story.sport.name,
            title = story.title,
            author = story.author,
            date = story.date,
            content = story.teaser
        )

        val viewState = storyViewModel.viewState

        coVerify { mockGetStory(storyId) }
        assertEquals(viewState, state)
    }
}
