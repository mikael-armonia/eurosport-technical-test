package com.mikaelarmonia.video.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mikaelarmonia.core.data.db.converters.LocalDateTimeConverter
import com.mikaelarmonia.core.data.db.entities.SportEntity
import com.mikaelarmonia.video.data.db.dao.VideoDao
import com.mikaelarmonia.video.data.db.entities.VideoEntity

@Database(entities = [VideoEntity::class, SportEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
internal abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}