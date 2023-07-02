package com.mikaelarmonia.video.data.api.model

import com.mikaelarmonia.core.data.api.model.ArticleDto
import com.mikaelarmonia.core.data.api.model.SportDto

data class VideoDto(
    override val id: Long,
    override val title: String,
    val thumb: String,
    val url: String,
    val views: Long,
    override val date: Double,
    override val sport: SportDto,
) : ArticleDto()
