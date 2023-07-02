package com.mikaelarmonia.feed.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mikaelarmonia.feed.R

@Composable
fun FeedLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Row {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = stringResource(R.string.loading))
        }
    }
}

@Preview
@Composable
private fun FeedLoadingPreview() {
    FeedLoading(modifier = Modifier.fillMaxSize())
}
