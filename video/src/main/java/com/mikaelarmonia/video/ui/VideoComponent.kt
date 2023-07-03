package com.mikaelarmonia.video.ui

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.mikaelarmonia.ui.toolbar.TopBarState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun VideoComponent(
    modifier: Modifier = Modifier,
    videoId: Long,
    topBarConfigurator: @Composable (TopBarState) -> Unit,
) {
    val videoViewModel: VideoViewModel = koinViewModel(parameters = { parametersOf(videoId) })
    val state by videoViewModel.viewStateFlow.collectAsState()

    topBarConfigurator(TopBarState())

    if (state.url.isNotBlank()) {
        VideoContent(
            modifier = modifier,
            url = state.url
        )
    }
}

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
private fun VideoContent(
    modifier: Modifier = Modifier,
    url: String
) {
    val context = LocalContext.current
    val exoPlayer = remember { buildExoPlayer(context, url) }

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                PlayerView(context).apply {
                    useController = true
                    resizeMode = AspectRatioFrameLayout. RESIZE_MODE_ZOOM

                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                }
            }
        )
    ) {
        onDispose { exoPlayer.release() }
    }
}

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
private fun buildExoPlayer(
    context: Context,
    url: String,
): ExoPlayer =
    ExoPlayer.Builder(
        context
    ).build()
    .apply {
        val defaultDataSourceFactory = DefaultHttpDataSource.Factory()
        val source = ProgressiveMediaSource.Factory(defaultDataSourceFactory)
            .createMediaSource(MediaItem.fromUri(url))

        setMediaSource(source)
        prepare()
        playWhenReady = true
    }
