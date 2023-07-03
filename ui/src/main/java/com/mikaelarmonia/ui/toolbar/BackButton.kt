package com.mikaelarmonia.ui.toolbar

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikaelarmonia.ui.R
import com.mikaelarmonia.ui.screen.repository.NavigatorRepository
import org.koin.androidx.compose.get

@Composable
fun BackButton(
    navigator: NavigatorRepository = get()
) {
    TopBarButton(
        modifier = Modifier.size(24.dp),
        iconRes = R.drawable.back,
        contentDescriptionRes = R.string.top_bar_back_button_content_description,
        onClick = { navigator.popBack() }
    )
}