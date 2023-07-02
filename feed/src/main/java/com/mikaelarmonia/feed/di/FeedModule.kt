package com.mikaelarmonia.feed.di

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.mikaelarmonia.feed.data.api.FeedService
import com.mikaelarmonia.feed.data.repository.FeedRepositoryImpl
import com.mikaelarmonia.feed.domain.FetchFeedData
import com.mikaelarmonia.feed.domain.StreamFeed
import com.mikaelarmonia.feed.domain.repository.FeedRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val feedModule = module {
    factory<Retrofit>(named("feed api")) {
        Retrofit.Builder()
            .baseUrl("https://extendsclass.com/api/json-storage/bin/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }
    factory<FeedService> { get<Retrofit>(named("feed api")).create(FeedService::class.java) }
    factoryOf(::FetchFeedData)
    factoryOf(::StreamFeed)

    single<FeedRepository> {
        FeedRepositoryImpl(
            feedService = get(),
            storyDataSource = get(named("story datasource")),
            videoDataSource = get(named("video datasource")),
        )
    }
}