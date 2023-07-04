package com.mikaelarmonia.story;

import com.mikaelarmonia.core.data.model.Sport;
import com.mikaelarmonia.story.data.model.Story;

import java.time.LocalDateTime;
import java.util.List;

public class StoryData {

    public static Story story1 = new Story(
            123,
            "Title",
            "Teaser",
            "https://image.image",
            "Author",
            LocalDateTime.now(),
            new Sport(
                    123,
                    "Sport"
            )
    );
    public static Story story2 = new Story(
            123,
            "Title2",
            "Teaser2",
            "https://image.image2",
            "Author2",
            LocalDateTime.now(),
            new Sport(
                    123,
                    "Sport2"
            )
    );

    public static List<Story> stories = List.of(
            story1,
            story2
    );
}
