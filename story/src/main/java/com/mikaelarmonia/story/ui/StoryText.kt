package com.mikaelarmonia.story.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikaelarmonia.story.R

@Composable
fun StoryText(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    date: String,
    content: String,
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                lineHeight = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.typography.titleSmall.color)) {
                    append(stringResource(id = R.string.description_short))
                    append(" ")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary)) {
                    append(author)
                }
            },
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = content,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
