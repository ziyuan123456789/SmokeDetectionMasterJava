package com.example.SmokeDetectionMaster.Bean.Pic;

import lombok.Data;

/**
 * @author ziyuan
 * @since 2024.04
 */
@Data
public class ScreenshotDTO {
    private String url;
    private int territoryId;
    private String territoryName;

    public ScreenshotDTO(String url, int territoryId,String territoryName) {
        this.url = url;
        this.territoryId = territoryId;
        this.territoryName=territoryName;
    }
}
