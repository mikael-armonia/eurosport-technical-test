package com.mikaelarmonia.feed.ui
import app.cash.turbine.test
import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.feed.data.FeedData.articles
import com.mikaelarmonia.feed.domain.FetchFeedData
import com.mikaelarmonia.feed.domain.StreamFeed
import com.mikaelarmonia.feed.ui.FeedViewModelData.initViewState
import com.mikaelarmonia.story.ui.screen.StoryScreen
import com.mikaelarmonia.ui.screen.repository.NavigatorRepository
import com.mikaelarmonia.video.ui.screen.VideoScreen
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FeedViewModelTest {
    private lateinit var mockFetchFeedData: FetchFeedData
    private lateinit var mockStreamFeed: StreamFeed
    private lateinit var mockNavigator: NavigatorRepository

    private lateinit var feedViewModel: FeedViewModel

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: TestScope

    @Before
    fun setup() {
        mockFetchFeedData = mockk(relaxed = true)
        mockStreamFeed = mockk()
        mockNavigator = mockk(relaxed = true)

        testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)

        // Mock the behavior of the streamFeed method
        val articlesFlow = MutableStateFlow<List<Article>>(articles)
        coEvery { mockStreamFeed() } returns articlesFlow

        feedViewModel = FeedViewModel(
            mockFetchFeedData,
            mockStreamFeed,
            mockNavigator
        )
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state is loading`() {
        val initialState = feedViewModel.viewStateFlow.value

        assert(initialState is State.Loading)
    }

    @Test
    fun `test fetchFeedData called on initialization`() = testScope.runTest {
        coVerify { mockFetchFeedData() }
    }

    @Test
    fun `test streamFeed called and updates view state`() = runTest {
        feedViewModel.viewStateFlow.test {
            advanceTimeBy(1000L)
            val viewState = awaitItem()

            // Verify that the view state was updated with the new articles
            assertEquals(viewState, initViewState)
        }
    }

    @Test
    fun `test dispatchIntent GoToStory`() {
        val storyId = 123L
        val intent = Intent.GoToStory(storyId)

        // Call the dispatchIntent method
        feedViewModel.dispatchIntent(intent)

        // Verify that navigator.navigateToScreen was called with the correct StoryScreen
        verify { mockNavigator.navigateToScreen(StoryScreen(storyId)) }
    }

    @Test
    fun `test dispatchIntent GoToVideo`() {
        val videoId = 456L
        val intent = Intent.GoToVideo(videoId)

        // Call the dispatchIntent method
        feedViewModel.dispatchIntent(intent)

        // Verify that navigator.navigateToScreen was called with the correct VideoScreen
        verify { mockNavigator.navigateToScreen(VideoScreen(videoId)) }
    }
}