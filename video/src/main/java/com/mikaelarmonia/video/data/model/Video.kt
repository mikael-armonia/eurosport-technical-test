package com.mikaelarmonia.video.data.model

import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.core.data.model.Sport
import java.time.LocalDateTime

data class Video(
    override val id: Long,
    override val title: String,
    val thumb: String,
    val url: String,
    val views: Long,
    override val date: LocalDateTime,
    override val sport: Sport,
) : Article()
