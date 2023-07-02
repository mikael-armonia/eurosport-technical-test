package com.mikaelarmonia.feed.domain.repository

interface FeedRepository {
    suspend fun fetchData(): Result<Unit>
}