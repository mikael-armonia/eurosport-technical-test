package com.mikaelarmonia.video;

import com.mikaelarmonia.core.data.api.model.SportDto;
import com.mikaelarmonia.video.data.api.model.VideoDto;

import java.util.List;

public class VideoDtoData {
    public static List<VideoDto> videoDtos = List.of(
            new VideoDto(
                    123,
                    "Title",
                    "https://thum.url",
                    "https://url.url",
                    1337,
                    12345678,
                    new SportDto(
                            123,
                            "Sport"
                    )
            )
    );
}
