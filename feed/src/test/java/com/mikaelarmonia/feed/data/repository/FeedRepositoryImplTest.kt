package com.mikaelarmonia.feed.data.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.feed.data.api.FeedService
import com.mikaelarmonia.feed.data.api.model.FeedDto
import com.mikaelarmonia.feed.data.repository.FeedDtoData.feedDto
import com.mikaelarmonia.story.StoryDtoData.storyDtos
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.data.model.toStory
import com.mikaelarmonia.video.VideoDtoData.videoDtos
import com.mikaelarmonia.video.data.model.Video
import com.mikaelarmonia.video.data.model.toVideo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class FeedRepositoryImplTest {

    private lateinit var mockFeedService: FeedService
    private lateinit var mockStoryDataSource: DataSource<Story>
    private lateinit var mockVideoDataSource: DataSource<Video>
    private lateinit var feedRepository: FeedRepositoryImpl

    @Before
    fun setup() {
        mockFeedService = mockk()
        mockStoryDataSource = mockk {
            coEvery { storeData(any()) } returns Result.success(Unit)
        }
        mockVideoDataSource = mockk {
            coEvery { storeData(any()) } returns Result.success(Unit)
        }

        feedRepository = FeedRepositoryImpl(
            mockFeedService,
            mockStoryDataSource,
            mockVideoDataSource
        )
    }

    @Test
    fun `test fetchData success`() = runBlocking {
        // Mock the successful network response from the feedService
        val networkResponse: NetworkResponse<FeedDto, String> = NetworkResponse.Success(feedDto, Response.success(feedDto))
        coEvery { mockFeedService.getFeed() } returns networkResponse

        // Mock the behavior of the data sources
        val stories = storyDtos.map { it.toStory() }
        val videos = videoDtos.map { it.toVideo() }
        coEvery { mockStoryDataSource.storeData(stories) } returns Result.success(Unit)
        coEvery { mockVideoDataSource.storeData(videos) } returns Result.success(Unit)

        // Call the method under test
        val result = feedRepository.fetchData()

        // Assert that the result is a success
        assert(result.isSuccess)
        assert(result.getOrNull() == Unit)

        // Verify that the storeData methods were called on the data sources
        coVerify { mockStoryDataSource.storeData(stories) }
        coVerify { mockVideoDataSource.storeData(videos) }
    }

    @Test
    fun `test fetchData error`() = runBlocking {
        // Mock the error network response from the feedService
        val errorMessage = "Network error"
        val networkResponse: NetworkResponse<FeedDto, String> = NetworkResponse.NetworkError(IOException(errorMessage))
        coEvery { mockFeedService.getFeed() } returns networkResponse

        // Call the method under test
        val result = feedRepository.fetchData()

        // Assert that the result is a failure
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == errorMessage)

        // Verify that the storeData methods were not called on the data sources
        coVerify(exactly = 0) { mockStoryDataSource.storeData(any()) }
        coVerify(exactly = 0) { mockVideoDataSource.storeData(any()) }
    }

    @Test
    fun `test fetchData with empty stories`() = runBlocking {
        // Mock the successful network response with an empty list of stories
        val feedDto = FeedDto(
            videos = videoDtos,
            stories = emptyList()
        )
        val networkResponse: NetworkResponse<FeedDto, String> = NetworkResponse.Success(feedDto, Response.success(feedDto))
        coEvery { mockFeedService.getFeed() } returns networkResponse

        // Call the method under test
        val result = feedRepository.fetchData()

        // Assert that the result is a success
        assert(result.isSuccess)
        assert(result.getOrNull() == Unit)

        // Verify that the storeData methods were called on the data sources with empty lists
        coVerify { mockStoryDataSource.storeData(emptyList()) }
        coVerify { mockVideoDataSource.storeData(feedDto.videos.map { it.toVideo() }) }
    }

    @Test
    fun `test fetchData with empty videos`() = runBlocking {
        // Mock the successful network response with an empty list of videos
        val feedDto = FeedDto(
            videos = emptyList(),
            stories = storyDtos
        )
        val networkResponse: NetworkResponse<FeedDto, String> = NetworkResponse.Success(feedDto, Response.success(feedDto))
        coEvery { mockFeedService.getFeed() } returns networkResponse

        // Call the method under test
        val result = feedRepository.fetchData()

        // Assert that the result is a success
        assert(result.isSuccess)
        assert(result.getOrNull() == Unit)

        // Verify that the storeData methods were called on the data sources with empty lists
        coVerify { mockStoryDataSource.storeData(feedDto.stories.map { it.toStory() }) }
        coVerify { mockVideoDataSource.storeData(emptyList()) }
    }

    @Test
    fun `test fetchData with error and null error message`() = runBlocking {
        // Mock the error network response from the feedService with a null error message
        val networkResponse: NetworkResponse<FeedDto, String> = NetworkResponse.UnknownError(Error("Unknown error"), null)
        coEvery { mockFeedService.getFeed() } returns networkResponse

        // Call the method under test
        val result = feedRepository.fetchData()

        // Assert that the result is a failure with a generic error message
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Unknown error")

        // Verify that the storeData methods were not called on the data sources
        coVerify(exactly = 0) { mockStoryDataSource.storeData(any()) }
        coVerify(exactly = 0) { mockVideoDataSource.storeData(any()) }
    }

    // Additional tests can be added for different scenarios, error handling, etc.
}
