package com.mikaelarmonia.feed.data;

import static com.mikaelarmonia.story.StoryData.story1;
import static com.mikaelarmonia.story.StoryData.story2;
import static com.mikaelarmonia.video.VideoData.video1;
import static com.mikaelarmonia.video.VideoData.video2;

import com.mikaelarmonia.core.data.model.Article;

import java.util.List;

public class FeedData {
    public static List<Article> articles = List.of(
            story1,
            video1,
            story2,
            video2
    );
}
