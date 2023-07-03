package com.mikaelarmonia.video.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mikaelarmonia.video.R
import com.mikaelarmonia.video.data.model.Video

@Composable
fun Video.videoDescription(): String = stringResource(R.string.views, views)

@Composable
fun Video.ThumbDecoration() {
    Image(
        modifier = Modifier.size(48.dp),
        painter = painterResource(id = com.mikaelarmonia.ui.R.drawable.play),
        contentDescription = stringResource(id = R.string.play_button_content_description)
    )
}
