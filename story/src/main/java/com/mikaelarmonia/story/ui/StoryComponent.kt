package com.mikaelarmonia.story.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mikaelarmonia.ui.toolbar.ShareActionButton
import com.mikaelarmonia.ui.toolbar.TopBarState
import com.mikaelarmonia.ui.utils.diffFromNow
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDateTime

@Composable
fun StoryComponent(
    modifier: Modifier = Modifier,
    storyId: Long,
    topBarConfigurator: @Composable (TopBarState) -> Unit
) {
    val viewModel: StoryViewModel = koinViewModel(parameters = { parametersOf(storyId) })
    val state: State by viewModel.viewStateFlow.collectAsState()
    val shareButton: @Composable (RowScope) -> Unit = {
        ShareActionButton()
    }

    topBarConfigurator(
        TopBarState(
            isBackEnabled = true,
            actions = listOf(shareButton)
        )
    )
    StoryContent(
        modifier = modifier,
        image = state.image,
        title = state.title,
        sport = state.sport,
        author = state.author,
        date = state.date,
        content = state.content
    )
}

@Composable
private fun StoryContent(
    modifier: Modifier = Modifier,
    image: String,
    title: String,
    sport: String,
    author: String,
    date: LocalDateTime,
    content: String,
) {
    val constraintSet = decoupledConstraints()

    ConstraintLayout(
        modifier = modifier,
        constraintSet = constraintSet
    ) {
        StoryImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(272.dp)
                .layoutId("image"),
            imageUrl = image,
        )
        StoryTag(
            modifier = Modifier
                .padding(8.dp)
                .layoutId("tag"),
            tag = sport
        )
        StoryText(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
                .layoutId("text"),
            title = title,
            author = author,
            date = date.diffFromNow(),
            content = content
        )
    }
}

private fun decoupledConstraints(): ConstraintSet = ConstraintSet {
    val imageRef = createRefFor("image")
    val tagRef = createRefFor("tag")
    val textRef = createRefFor("text")

    val guideline = createGuidelineFromTop(272.dp)

    constrain(imageRef) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
        end.linkTo(parent.end)
        bottom.linkTo(textRef.top)
        verticalChainWeight = 0f
    }
    constrain(textRef) {
        start.linkTo(parent.start)
        top.linkTo(imageRef.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
    constrain(tagRef) {
        start.linkTo(parent.start)
        top.linkTo(guideline)
        bottom.linkTo(guideline)
    }
}
