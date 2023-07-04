package com.mikaelarmonia.story;

import com.mikaelarmonia.core.data.model.Sport;
import com.mikaelarmonia.story.data.model.Story;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class StoryData {

    public static Story story1 = new Story(
            123L,
            "Title",
            "Teaser",
            "https://image.url",
            "Author",
            LocalDateTime.ofEpochSecond(123456789L, 123, ZoneOffset.UTC),
            new Sport(
                    123L,
                    "Sport"
            )
    );
    public static Story story2 = new Story(
            123L,
            "Title2",
            "Teaser2",
            "https://image.image2",
            "Author2",
            LocalDateTime.now(),
            new Sport(
                    123L,
                    "Sport2"
            )
    );

    public static List<Story> stories = List.of(
            story1,
            story2
    );
}
