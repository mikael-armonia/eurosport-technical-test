package com.mikaelarmonia.video.data.model

import com.mikaelarmonia.core.data.model.toSport
import com.mikaelarmonia.core.data.model.toSportEntity
import com.mikaelarmonia.video.data.db.entities.VideoEntity

internal fun Video.toVideoEntity() = VideoEntity(
    id = id,
    title = title,
    thumb = thumb,
    url = url,
    views = views,
    date = date,
    sport = sport.toSportEntity(),
)

internal fun VideoEntity.toVideo() = Video(
    id = id,
    title = title,
    thumb = thumb,
    url = url,
    views = views,
    date = date,
    sport = sport.toSport(),
)
