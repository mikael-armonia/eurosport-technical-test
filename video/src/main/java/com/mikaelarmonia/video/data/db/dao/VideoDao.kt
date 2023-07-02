package com.mikaelarmonia.video.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mikaelarmonia.video.data.db.entities.VideoEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface VideoDao {
    @Query("SELECT * FROM VideoEntity")
    fun streamAll(): Flow<List<VideoEntity>>

    @Insert
    suspend fun insertAll(vararg videos: VideoEntity)

    @Query("DELETE FROM VideoEntity")
    suspend fun deleteAll()
}
