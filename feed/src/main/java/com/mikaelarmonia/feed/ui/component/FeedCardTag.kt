package com.mikaelarmonia.feed.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FeedCardTag(
    modifier: Modifier = Modifier,
    tag: String
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(4.dp)
            ).padding(4.dp)
    ) {
        Text(
            text = tag.uppercase(),
            color = MaterialTheme.colorScheme.background,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
