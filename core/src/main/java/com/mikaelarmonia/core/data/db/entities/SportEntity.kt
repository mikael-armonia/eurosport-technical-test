package com.mikaelarmonia.core.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SportEntity(
    @PrimaryKey val id: Long,
    val name: String,
)
