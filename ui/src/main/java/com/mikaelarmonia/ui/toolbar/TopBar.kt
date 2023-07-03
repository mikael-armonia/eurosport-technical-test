package com.mikaelarmonia.ui.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mikaelarmonia.ui.R
import com.mikaelarmonia.ui.screen.repository.NavigatorRepository
import org.koin.androidx.compose.get

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    backgroundColor: Color,
    isBackEnabled: Boolean,
    actions: List<@Composable (RowScope) -> Unit>
) {
    val navigator: NavigatorRepository = get()

    Row(
        modifier = modifier
            .background(color = backgroundColor)
            .fillMaxWidth()
            .height(height = 56.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isBackEnabled) {
            Image(
                modifier = Modifier.size(48.dp)
                    .clickable { navigator.popBack() },
                painter = painterResource(id = R.drawable.back),
                contentDescription =
                stringResource(id = R.string.top_bar_back_button_content_description)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.background
            )
        }
        actions.forEach { it(this) }
    }
}