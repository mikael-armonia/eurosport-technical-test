package com.mikaelarmonia.feed.domain

import com.mikaelarmonia.feed.domain.repository.FeedRepository

class FetchFeedData(
    private val repository: FeedRepository
) {
    suspend operator fun invoke() = repository.fetchData()
}
