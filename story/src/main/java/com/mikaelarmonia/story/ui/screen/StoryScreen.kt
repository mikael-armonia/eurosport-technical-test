package com.mikaelarmonia.story.ui.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mikaelarmonia.ui.screen.Screen

data class StoryScreen(
    val id: Long
) : Screen(params = listOf(id)) {
    override val baseRoute: String = Companion.baseRoute
    override val navArgs: List<NamedNavArgument> = Companion.navArgs

    companion object : Screen() {
        override val baseRoute: String = "story/{storyId}"
        override val navArgs: List<NamedNavArgument> = listOf(
            navArgument(name = "storyId") {
                type = NavType.LongType
            }
        )
    }
}
