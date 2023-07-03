package com.mikaelarmonia.ui.toolbar

import android.content.Intent
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mikaelarmonia.ui.R

@Composable
fun ShareActionButton(
    linkToShare: String = "https://www.eurosport.fr/"
) {
    val context = LocalContext.current
    TopBarButton(
        modifier = Modifier.size(24.dp),
        iconRes = R.drawable.share,
        contentDescriptionRes = R.string.top_bar_share_button_content_description,
        onClick = {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, linkToShare)
                type = "text/html"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
    )
}
