package com.mikaelarmonia.feed.data.repository;

import com.mikaelarmonia.feed.data.api.model.FeedDto;
import com.mikaelarmonia.story.StoryDtoData;
import com.mikaelarmonia.video.VideoDtoData;

public class FeedDtoData {
    public static FeedDto feedDto = new FeedDto(
            VideoDtoData.videoDtos,
            StoryDtoData.storyDtos
    );
}
