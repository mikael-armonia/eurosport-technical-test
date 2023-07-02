package com.mikaelarmonia.ui.screen

import android.util.Log
import androidx.navigation.NamedNavArgument

private val TAG = Screen::class.simpleName

abstract class Screen(
    private val params: List<Any> = emptyList()
) {
    abstract val baseRoute: String
    abstract val navArgs: List<NamedNavArgument>

    fun destination(): String {
        return when (params.size) {
            navArgs.size -> {
                navArgs.foldIndexed(baseRoute) { i, destination, arg ->
                    destination.replace("{${arg.name}}", "${params[i]}")
                }
            }
            else -> baseRoute.also {
                Log.e(TAG, "Wrong parameters for screen ${javaClass.name}, params size : ${params.size}, args size : ${navArgs.size}")
            }
        }
    }
}