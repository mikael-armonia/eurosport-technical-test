package com.mikaelarmonia.story.data.api.model

import com.mikaelarmonia.core.data.api.model.ArticleDto
import com.mikaelarmonia.core.data.api.model.SportDto

data class StoryDto(
    override val id: Long,
    override val title: String,
    val teaser: String,
    val author: String,
    override val date: Double,
    override val sport: SportDto
) : ArticleDto()
