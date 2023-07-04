package com.mikaelarmonia.video;

import com.mikaelarmonia.core.data.db.entities.SportEntity;
import com.mikaelarmonia.video.data.db.entities.VideoEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class VideoEntityData {
    public static VideoEntity videoEntity1 = new VideoEntity(
            123,
            "Title",
            "https://thumb.url",
            "https://url.url",
            1337,
            LocalDateTime.ofEpochSecond(123456789L, 123, ZoneOffset.UTC),
            new SportEntity(
                    123,
                    "Sport"
            )
    );
}
