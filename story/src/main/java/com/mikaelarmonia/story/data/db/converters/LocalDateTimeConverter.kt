package com.mikaelarmonia.story.data.db.converters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class LocalDateTimeConverter {
    @TypeConverter
    fun localDateTimeFromTimestamp(timestamp: Long): LocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(timestamp),
        ZoneOffset.UTC.normalized()
    ).atZone(ZoneId.systemDefault()).toLocalDateTime()

    @TypeConverter
    fun localDateTimeToTimestamp(localDateTime: LocalDateTime): Long =
        localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
}