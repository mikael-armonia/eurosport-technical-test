package com.mikaelarmonia.feed.ui.screen

import androidx.navigation.NamedNavArgument
import com.mikaelarmonia.ui.screen.Screen

object FeedScreen : Screen() {
    override val baseRoute: String = "feed"
    override val navArgs: List<NamedNavArgument> = emptyList()
}