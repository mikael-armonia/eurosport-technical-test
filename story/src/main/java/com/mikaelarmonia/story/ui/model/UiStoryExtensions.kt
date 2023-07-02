package com.mikaelarmonia.story.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mikaelarmonia.story.R
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.ui.utils.diffFromNow

@Composable
fun Story.storyDescription(): String = stringResource(
    id = R.string.description,
    author,
    date.diffFromNow()
)
