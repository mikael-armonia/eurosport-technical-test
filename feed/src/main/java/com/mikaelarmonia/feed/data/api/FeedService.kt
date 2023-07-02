package com.mikaelarmonia.feed.data.api

import com.haroldadmin.cnradapter.NetworkResponse
import com.mikaelarmonia.feed.data.api.model.FeedDto
import retrofit2.http.GET

internal interface FeedService {
    @GET("edfefba")
    suspend fun getFeed(): NetworkResponse<FeedDto, String>
}