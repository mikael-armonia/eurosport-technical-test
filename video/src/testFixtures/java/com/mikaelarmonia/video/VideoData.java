package com.mikaelarmonia.video;

import com.mikaelarmonia.core.data.model.Sport;
import com.mikaelarmonia.video.data.model.Video;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class VideoData {

    public static Sport sport1 = new Sport(
            123L,
            "Sport"
    );

    public static Video video1 = new Video(
            123,
            "Title",
            "https://thumb.url",
            "https://url.url",
            1337,
            LocalDateTime.ofEpochSecond(123456789L, 123, ZoneOffset.UTC),
            sport1
    );
    public static Video video2 = new Video(
            123,
            "Title 2",
            "https://thum.url2",
            "https://url.url2",
            12345,
            LocalDateTime.now(),
            new Sport(
                    123,
                    "Sport2"
            )
    );

    public static List<Video> videos = List.of(
            video1,
            video2
    );
}
