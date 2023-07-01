package com.mikaelarmonia.video.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikaelarmonia.core.data.db.entities.ArticleEntity
import com.mikaelarmonia.core.data.db.entities.SportEntity
import java.time.LocalDateTime

@Entity
internal data class VideoEntity(
    @PrimaryKey override val id: Long,
    override val title: String,
    val thumb: String,
    val url: String,
    val views: Long,
    override val date: LocalDateTime,
    @Embedded("sport_") override val sport: SportEntity,
) : ArticleEntity()
