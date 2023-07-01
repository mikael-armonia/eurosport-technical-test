package com.mikaelarmonia.story.data.model

import com.mikaelarmonia.core.data.model.toSport
import com.mikaelarmonia.core.data.model.toSportEntity
import com.mikaelarmonia.story.data.db.entities.StoryEntity

internal fun Story.toStoryEntity() = StoryEntity(
    id = id,
    title = title,
    teaser = teaser,
    author = author,
    date = date,
    sport = sport.toSportEntity(),
)

internal fun StoryEntity.toStory() = Story(
    id = id,
    title = title,
    teaser = teaser,
    author = author,
    date = date,
    sport = sport.toSport(),
)
