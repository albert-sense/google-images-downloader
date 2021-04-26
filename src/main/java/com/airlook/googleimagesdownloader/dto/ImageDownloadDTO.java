package com.airlook.googleimagesdownloader.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: shenyonggang
 * @date: 2021-04-22 09:08
 */
@Data
@Accessors(chain = true)
public class ImageDownloadDTO {
    
    private Double startLongitude;
    
    private Double startLatitude;
    
    private Double endLongitude;
    
    private Double endLatitude;
    
    private Integer startZoom;
    
    private Integer endZoom;
    
    private String savePath;
}
