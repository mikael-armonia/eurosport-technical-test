package com.mikaelarmonia.eurosporttechnicaltest.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mikaelarmonia.feed.ui.component.FeedComponent
import com.mikaelarmonia.feed.ui.screen.FeedScreen
import com.mikaelarmonia.story.ui.StoryComponent
import com.mikaelarmonia.story.ui.screen.StoryScreen
import com.mikaelarmonia.ui.toolbar.TopBarState
import com.mikaelarmonia.video.ui.VideoComponent
import com.mikaelarmonia.video.ui.screen.VideoScreen

internal fun NavGraphBuilder.eurosportTTNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
    topBarConfigurator: @Composable (TopBarState) -> Unit,
) {
    composable(FeedScreen.baseRoute) {
        FeedComponent(
            modifier = modifier,
            topBarConfigurator = topBarConfigurator
        )
    }
    composable(StoryScreen.baseRoute) { navBackStackEntry ->
        val storyId = navBackStackEntry.arguments?.getString("storyId")?.toLong()
        storyId?.let {
            StoryComponent(
                modifier = modifier,
                storyId = it,
                topBarConfigurator = topBarConfigurator
            )
        }
    }
    composable(VideoScreen.baseRoute) { navBackStackEntry ->
        val videoId = navBackStackEntry.arguments?.getString("videoId")?.toLong()
        videoId?.let {
            VideoComponent(
                modifier = modifier,
                videoId = it,
                topBarConfigurator = topBarConfigurator,
            )
        }
    }
}
