package com.mikaelarmonia.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mikaelarmonia.ui.R
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
fun LocalDateTime.diffFromNow(): String {
    val now = LocalDateTime.now()
    val minuteBetween = ChronoUnit.MINUTES.between(this, now)

    return stringResource(
        id = R.string.ago,
        when {
            // Minutes
            minuteBetween < 60 -> stringResource(id = R.string.min, minuteBetween)
            // Hours
            minuteBetween < 1440 -> stringResource(
                id = R.string.hours,
                ChronoUnit.HOURS.between(this, now)
            )
            // Days
            else -> stringResource(
                id = R.string.days,
                ChronoUnit.DAYS.between(this, now)
            )
        }
    )
}
