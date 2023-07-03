package com.mikaelarmonia.eurosporttechnicaltest.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mikaelarmonia.feed.ui.component.FeedComponent
import com.mikaelarmonia.feed.ui.screen.FeedScreen
import com.mikaelarmonia.ui.screen.PopBack
import com.mikaelarmonia.ui.screen.repository.DestinationRepository
import com.mikaelarmonia.ui.toolbar.TopBarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun NavGraphBuilder.eurosportTTNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
    destinationRepository: DestinationRepository,
    topBarConfigurator: @Composable (TopBarState) -> Unit,
    coroutineScope: CoroutineScope,
) {
    coroutineScope.launch {
        destinationRepository.streamDestination().collect { screen ->
            when (screen) {
                PopBack -> navController.popBackStack()
                else -> navController.navigate(screen.destination())
            }
        }
    }

    composable(FeedScreen.baseRoute) {
        FeedComponent(
            modifier = modifier.fillMaxSize(),
            topBarConfigurator = topBarConfigurator
        )
    }
}
