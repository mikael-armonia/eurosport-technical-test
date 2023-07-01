package com.mikaelarmonia.story.data.db

import androidx.room.Database
import com.mikaelarmonia.story.data.db.dao.StoryDao
import com.mikaelarmonia.story.data.db.entities.StoryEntity

@Database(entities = [StoryEntity::class], version = 1)
abstract class StoryDatabase {
    abstract fun storyDao(): StoryDao
}