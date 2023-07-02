package com.mikaelarmonia.video.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mikaelarmonia.video.R
import com.mikaelarmonia.video.data.model.Video

@Composable
fun Video.videoDescription(): String = stringResource(R.string.views, views)
