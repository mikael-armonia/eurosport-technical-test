package com.mikaelarmonia.video.data.datasource

import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.video.data.db.dao.VideoDao
import com.mikaelarmonia.video.data.model.Video
import com.mikaelarmonia.video.data.model.toVideo
import com.mikaelarmonia.video.data.model.toVideoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class VideoLocalDataSource(
    private val videoDao: VideoDao,
) : DataSource<Video> {

    override fun streamData(): Flow<List<Video>> = videoDao.streamAll().map { entities ->
        entities.map { it.toVideo() }
    }

    override suspend fun storeData(data: List<Video>): Result<Unit> = Result.runCatching {
        val videos = data.map { it.toVideoEntity() }.toTypedArray()
        videoDao.deleteAll()
        videoDao.insertAll(*videos)
    }
}
