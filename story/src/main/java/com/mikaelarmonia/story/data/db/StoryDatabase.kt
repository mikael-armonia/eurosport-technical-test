package com.mikaelarmonia.story.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mikaelarmonia.core.data.db.entities.SportEntity
import com.mikaelarmonia.story.data.db.converters.LocalDateTimeConverter
import com.mikaelarmonia.story.data.db.dao.StoryDao
import com.mikaelarmonia.story.data.db.entities.StoryEntity

@Database(entities = [StoryEntity::class, SportEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class StoryDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
}