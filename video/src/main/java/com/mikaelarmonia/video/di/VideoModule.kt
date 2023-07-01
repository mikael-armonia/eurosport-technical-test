package com.mikaelarmonia.video.di

import androidx.room.Room
import com.mikaelarmonia.video.domain.repository.VideoRepository
import com.mikaelarmonia.video.data.datasource.VideoLocalDataSource
import com.mikaelarmonia.video.data.db.VideoDatabase
import com.mikaelarmonia.video.data.db.dao.VideoDao
import com.mikaelarmonia.video.data.repository.VideoRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
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

    singleOf(::VideoLocalDataSource)
    single<VideoRepository> { VideoRepositoryImpl(localDataSource = get()) }
}