package com.mikaelarmonia.video.ui.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mikaelarmonia.ui.screen.Screen

data class VideoScreen(
    val videoId: Long
) : Screen(params = listOf(videoId)) {
    override val baseRoute: String = Companion.baseRoute
    override val navArgs: List<NamedNavArgument> = Companion.navArgs

    companion object : Screen() {
        override val baseRoute: String = "video/{videoId}"
        override val navArgs: List<NamedNavArgument> = listOf(
            navArgument(name = "videoId") {
                type = NavType.LongType
            }
        )
    }
}