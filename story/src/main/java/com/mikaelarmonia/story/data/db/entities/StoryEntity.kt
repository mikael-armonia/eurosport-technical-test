package com.mikaelarmonia.story.data.db.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikaelarmonia.core.data.db.entities.ArticleEntity
import com.mikaelarmonia.core.data.db.entities.SportEntity
import java.time.LocalDateTime

@Entity
internal data class StoryEntity(
    @PrimaryKey override val id: Long,
    override val title: String,
    val teaser: String,
    val author: String,
    override val date: LocalDateTime,
    @Embedded("sport_") override val sport: SportEntity,
) : ArticleEntity()
