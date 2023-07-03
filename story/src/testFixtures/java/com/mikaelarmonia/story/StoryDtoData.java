package com.mikaelarmonia.story;

import com.mikaelarmonia.core.data.api.model.SportDto;
import com.mikaelarmonia.story.data.api.model.StoryDto;

import java.util.List;

public class StoryDtoData {
    public static List<StoryDto> storyDtos = List.of(
            new StoryDto(
                    123,
                    "Title",
                    "Tease",
                    "https://image.image",
                    "Author",
                    12345678,
                    new SportDto(
                            123,
                            "Sport"
                    )
            )
    );
}
