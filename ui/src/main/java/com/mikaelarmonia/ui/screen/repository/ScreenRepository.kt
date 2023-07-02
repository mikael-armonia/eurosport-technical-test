package com.mikaelarmonia.ui.screen.repository

import com.mikaelarmonia.ui.screen.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ScreenRepository(
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : DestinationRepository, NavigatorRepository, CoroutineScope {
    private val screenStream = MutableSharedFlow<Screen>()

    override fun streamDestination(): Flow<Screen> = screenStream

    override fun navigateToScreen(screen: Screen) {
        launch { screenStream.emit(screen) }
    }
}