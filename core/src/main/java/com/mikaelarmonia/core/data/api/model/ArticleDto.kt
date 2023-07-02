package com.mikaelarmonia.core.data.api.model

abstract class ArticleDto {
    abstract val id: Long
    abstract val title: String
    abstract val date: Double
    abstract val sport: SportDto
}