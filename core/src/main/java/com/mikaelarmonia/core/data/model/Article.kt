package com.mikaelarmonia.core.data.model

import java.time.LocalDateTime

abstract class Article {
    abstract val id: Long
    abstract val title: String
    abstract val date: LocalDateTime
    abstract val sport: Sport
}
