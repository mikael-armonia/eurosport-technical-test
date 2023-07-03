package com.mikaelarmonia.ui.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class TopBarState(
    val title: String = "",
    val backgroundColor: Color = Color.Unspecified,
    val isBackEnabled: Boolean = false,
    val actions: List<@Composable (RowScope) -> Unit> = emptyList(),
)
