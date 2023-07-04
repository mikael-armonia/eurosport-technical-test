package com.mikaelarmonia.story;

import com.mikaelarmonia.core.data.db.entities.SportEntity;
import com.mikaelarmonia.story.data.db.entities.StoryEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class StoryEntityData {
    public static StoryEntity storyEntity1 = new StoryEntity(
            123L,
            "Title",
            "Teaser",
            "https://image.url",
            "Author",
            LocalDateTime.ofEpochSecond(123456789L, 123, ZoneOffset.UTC),
            new SportEntity(
                    123L,
                    "Sport"
            )
    );
}
