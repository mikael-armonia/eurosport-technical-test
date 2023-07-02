package com.mikaelarmonia.core.data.model

import com.mikaelarmonia.core.data.api.model.SportDto
import com.mikaelarmonia.core.data.db.entities.SportEntity

fun Sport.toSportEntity() = SportEntity(
    id = id,
    name = name,
)

fun SportEntity.toSport() = Sport(
    id = id,
    name = name,
)

fun SportDto.toSport() = Sport(
    id = id,
    name = name,
)
