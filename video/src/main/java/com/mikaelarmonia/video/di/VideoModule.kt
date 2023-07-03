package com.mikaelarmonia.video.di

import androidx.room.Room
import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.video.data.datasource.VideoLocalDataSource
import com.mikaelarmonia.video.data.db.VideoDatabase
import com.mikaelarmonia.video.data.db.dao.VideoDao
import com.mikaelarmonia.video.data.model.Video
import com.mikaelarmonia.video.data.repository.VideoRepositoryImpl
import com.mikaelarmonia.video.domain.repository.GetVideo
import com.mikaelarmonia.video.domain.repository.VideoRepository
import com.mikaelarmonia.video.ui.VideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val videoModule = module {
    factory<VideoDatabase> {
        Room.databaseBuilder(
            androidContext(),
            VideoDatabase::class.java,
            "video-database"
        ).build()
    }
    factory<VideoDao> { get<VideoDatabase>().videoDao() }
    factoryOf(::GetVideo)

    viewModel { (videoId: Long) ->
        VideoViewModel(
            videoId = videoId,
            getVideo = get()
        )
    }

    singleOf(::VideoLocalDataSource)
    single<DataSource<Video>>(named("video datasource")) {
        get<VideoLocalDataSource>()
    }
    single<VideoRepository> {
        VideoRepositoryImpl(localDataSource = get())
    }
}
