package com.mikaelarmonia.story.data.model

import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.core.data.model.Sport
import java.time.LocalDateTime

data class Story(
    override val id: Long,
    override val title: String,
    val teaser: String,
    val image: String,
    val author: String,
    override val date: LocalDateTime,
    override val sport: Sport,
) : Article()
