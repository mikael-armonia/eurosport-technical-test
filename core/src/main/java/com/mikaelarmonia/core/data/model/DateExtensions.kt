package com.mikaelarmonia.core.data.model

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun Double.toLocalDateTime() = LocalDateTime.ofInstant(
    Instant.ofEpochMilli(this.div(1000).toLong()),
    ZoneOffset.UTC.normalized()
).atZone(ZoneId.systemDefault()).toLocalDateTime()