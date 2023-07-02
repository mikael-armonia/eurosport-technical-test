package com.mikaelarmonia.ui.screen.repository

import com.mikaelarmonia.ui.screen.Screen

interface NavigatorRepository {
    fun navigateToScreen(screen: Screen)
}