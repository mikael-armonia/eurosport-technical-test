package com.mikaelarmonia.story.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mikaelarmonia.story.data.db.entities.StoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface StoryDao {

    @Query("SELECT * FROM StoryEntity WHERE id == :id")
    suspend fun get(id: Long): StoryEntity

    @Query("SELECT * FROM StoryEntity")
    fun streamAll(): Flow<List<StoryEntity>>

    @Insert
    suspend fun insertAll(vararg stories: StoryEntity)

    @Query("DELETE FROM StoryEntity")
    suspend fun deleteAll()
}
