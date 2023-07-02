package com.mikaelarmonia.eurosporttechnicaltest.di

import com.mikaelarmonia.feed.di.feedModule
import com.mikaelarmonia.story.di.storyModule
import com.mikaelarmonia.video.di.videoModule

val appModules = listOf(
    storyModule,
    videoModule,
    feedModule,
)