package com.mikaelarmonia.story.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mikaelarmonia.core.data.db.entities.ArticleEntity
import com.mikaelarmonia.core.data.model.Sport
import java.time.LocalDateTime

@Entity
data class StoryEntity(
    @PrimaryKey override val id: Long,
    override val title: String,
    val teaser: String,
    val author: String,
    override val date: LocalDateTime,
    override val sport: Sport
) : ArticleEntity()
