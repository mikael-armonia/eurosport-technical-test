package com.mikaelarmonia.core.data.db.entities

import androidx.room.Entity
import java.time.LocalDateTime

@Entity
abstract class ArticleEntity {
    abstract val id: Long
    abstract val title: String
    abstract val date: LocalDateTime
    abstract val sport: SportEntity
}
