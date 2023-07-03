package com.mikaelarmonia.story.data.datasource

import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.story.data.db.dao.StoryDao
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.data.model.toStory
import com.mikaelarmonia.story.data.model.toStoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class StoryLocalDataSource(
    private val storyDao: StoryDao,
) : DataSource<Story> {

    suspend fun getData(id: Long) = storyDao.get(id)

    override fun streamData(): Flow<List<Story>> = storyDao.streamAll().map { entities ->
        entities.map { it.toStory() }
    }

    override suspend fun storeData(data: List<Story>): Result<Unit> = Result.runCatching {
        val stories = data.map { it.toStoryEntity() }.toTypedArray()
        storyDao.deleteAll()
        storyDao.insertAll(*stories)
    }
}
