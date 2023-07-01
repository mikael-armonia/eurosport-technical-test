package com.mikaelarmonia.story.data.datasource

import com.mikaelarmonia.core.data.datasource.DataSource
import com.mikaelarmonia.story.data.db.dao.StoryDao
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.data.model.toStory
import com.mikaelarmonia.story.data.model.toStoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoryLocalDataSource(
    private val storyDao: StoryDao,
) : DataSource<Story> {

    override fun streamData(): Flow<List<Story>> = storyDao.streamAll().map { entities ->
        entities.map { it.toStory() }
    }

    override suspend fun storeData(data: List<Story>): Result<Unit> = Result.runCatching {
        val stories = data.map { it.toStoryEntity() }.toTypedArray()
        storyDao.insertAll(*stories)
    }
}
