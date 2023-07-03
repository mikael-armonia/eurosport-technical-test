package com.mikaelarmonia.ui.toolbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun TopBarButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    @StringRes contentDescriptionRes: Int,
    onClick: () -> Unit = {}
) {
    Image(
        modifier = modifier.clickable { onClick() },
        painter = painterResource(id = iconRes),
        contentDescription = stringResource(id = contentDescriptionRes)
    )
}
