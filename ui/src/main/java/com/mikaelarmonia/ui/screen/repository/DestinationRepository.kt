package com.mikaelarmonia.ui.screen.repository

import com.mikaelarmonia.ui.screen.Screen
import kotlinx.coroutines.flow.Flow

interface DestinationRepository {
    fun streamDestination(): Flow<Screen>
}