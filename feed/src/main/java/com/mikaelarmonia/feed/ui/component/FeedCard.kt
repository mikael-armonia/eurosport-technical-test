package com.mikaelarmonia.feed.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.mikaelarmonia.core.data.model.Article
import com.mikaelarmonia.story.data.model.Story
import com.mikaelarmonia.story.ui.model.storyDescription
import com.mikaelarmonia.story.ui.screen.StoryScreen
import com.mikaelarmonia.ui.screen.repository.NavigatorRepository
import com.mikaelarmonia.video.data.model.Video
import com.mikaelarmonia.video.ui.ThumbDecoration
import com.mikaelarmonia.video.ui.screen.VideoScreen
import com.mikaelarmonia.video.ui.videoDescription
import org.koin.androidx.compose.get

@Composable
fun FeedCard(
    modifier: Modifier = Modifier,
    article: Article,
) {
    val navigator: NavigatorRepository = get()
    var description = ""
    var imageUrl = ""
    var imageDecoration: @Composable () -> Unit = {}
    var onClick: () -> Unit = {}
    when (article) {
        is Story -> {
            description = article.storyDescription()
            imageUrl = article.image
            onClick = { navigator.navigateToScreen(StoryScreen(article.id)) }
        }
        is Video -> {
            description = article.videoDescription()
            imageUrl = article.thumb
            imageDecoration = { ThumbDecoration() }
            onClick = { navigator.navigateToScreen(VideoScreen(article.id)) }
        }
    }

    FeedCardContent(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable(onClick = onClick),
        imageUrl = imageUrl,
        tag = article.sport.name,
        title = article.title,
        description = description,
        imageDecoration = imageDecoration
    )
}

@Composable
private fun FeedCardContent(
    modifier: Modifier = Modifier,
    imageUrl: String,
    tag: String,
    title: String,
    description: String,
    imageDecoration: @Composable () -> Unit = {},
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        val constraintSet = decoupledConstraints()

        ConstraintLayout(constraintSet = constraintSet) {
            FeedCardImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .layoutId("image"),
                imageUrl = imageUrl,
                imageDecoration = imageDecoration,
            )
            FeedCardTag(
                modifier = Modifier
                    .padding(8.dp)
                    .layoutId("tag"),
                tag = tag
            )
            FeedCardText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 8.dp)
                    .layoutId("text"),
                title = title,
                description = description,
            )
        }
    }
}

private fun decoupledConstraints(): ConstraintSet = ConstraintSet {
    val imageRef = createRefFor("image")
    val tagRef = createRefFor("tag")
    val textRef = createRefFor("text")

    val guideline = createGuidelineFromTop(240.dp)

    constrain(imageRef) {
        start.linkTo(parent.start)
        top.linkTo(parent.top)
        end.linkTo(parent.end)
        bottom.linkTo(guideline)
    }
    constrain(textRef) {
        start.linkTo(parent.start)
        top.linkTo(tagRef.bottom)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }
    constrain(tagRef) {
        start.linkTo(parent.start)
        top.linkTo(guideline)
        bottom.linkTo(guideline)
    }
}
