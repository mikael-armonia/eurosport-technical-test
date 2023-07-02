package com.mikaelarmonia.eurosporttechnicaltest.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mikaelarmonia.feed.ui.component.FeedComponent
import com.mikaelarmonia.feed.ui.screen.FeedScreen
import com.mikaelarmonia.ui.screen.repository.DestinationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun NavGraphBuilder.eurosportTTNavGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
    destinationRepository: DestinationRepository,
    coroutineScope: CoroutineScope,
) {
    coroutineScope.launch {
        destinationRepository.streamDestination().collect { screen ->
            navController.navigate(screen.destination())
        }
    }

    composable(FeedScreen.baseRoute) {
        FeedComponent(modifier = modifier.fillMaxSize())
    }
}
