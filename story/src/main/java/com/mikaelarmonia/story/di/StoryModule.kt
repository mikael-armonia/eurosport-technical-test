package com.mikaelarmonia.story.di

import androidx.room.Room
import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.story.data.datasource.StoryLocalDataSource
import com.mikaelarmonia.story.data.db.StoryDatabase
import com.mikaelarmonia.story.data.db.dao.StoryDao
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.data.repository.StoryRepositoryImpl
import com.mikaelarmonia.story.domain.GetStory
import com.mikaelarmonia.story.domain.repository.StoryRepository
import com.mikaelarmonia.story.ui.StoryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val storyModule = module {
    factory<StoryDatabase> {
        Room.databaseBuilder(
            androidContext(),
            StoryDatabase::class.java,
            "story-database"
        ).fallbackToDestructiveMigration().build()
    }
    factory<StoryDao> { get<StoryDatabase>().storyDao() }
    factoryOf(::GetStory)

    viewModel { (storyId: Long) ->
        StoryViewModel(
            storyId = storyId,
            getStory = get()
        )
    }

    singleOf(::StoryLocalDataSource)
    single<DataSource<Story>>(named("story datasource")) {
        get<StoryLocalDataSource>()
    }
    single<StoryRepository> {
        StoryRepositoryImpl(localDataSource = get())
    }
}
