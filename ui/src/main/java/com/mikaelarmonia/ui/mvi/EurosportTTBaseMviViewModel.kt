package com.mikaelarmonia.ui.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class EurosportTTBaseMviViewModel<ST, I>(
    baseStateInitializer: () -> ST
) : ViewModel() {

    var viewState: ST = baseStateInitializer()
        set(value) {
            field = value
            _viewStateFlow.value = value
        }

    private val _viewStateFlow = MutableStateFlow(viewState)
    val viewStateFlow: StateFlow<ST> = _viewStateFlow

    abstract fun dispatchIntent(intent: I)

    inline fun <reified T : ST> updateViewState(newStateProvider: T.() -> T) {
        val state = viewState
        val newViewState = with(T::class) {
            when (state) {
                is T -> state
                else -> when {
                    java.constructors.isNotEmpty() ->
                        java.getDeclaredConstructor().newInstance()

                    objectInstance != null -> objectInstance
                    else -> throw IllegalStateException("State ${T::class} must have an empty constructor")
                }
            }?.newStateProvider()
        }
        newViewState?.let { viewState = it }
    }
}