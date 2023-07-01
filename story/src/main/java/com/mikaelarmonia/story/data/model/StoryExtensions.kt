package com.mikaelarmonia.story.data.model

import com.mikaelarmonia.story.data.db.entities.StoryEntity

fun Story.toStoryEntity() = StoryEntity(
    id = id,
    title = title,
    teaser = teaser,
    author = author,
    date = date,
    sport = sport,
)

fun StoryEntity.toStory() = Story(
    id = id,
    title = title,
    teaser = teaser,
    author = author,
    date = date,
    sport = sport,
)
