package com.airlook.googleimagesdownloader.controller;


import com.airlook.googleimagesdownloader.dto.ImageDownloadDTO;
import com.airlook.googleimagesdownloader.service.DownloadGoogleImageService;
import com.airlook.googleimagesdownloader.utils.GoogleImageUtil;
import com.airlook.googleimagesdownloader.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * <p>
 * 源数据管理控制器
 * </p>
 *
 * @author shenyonggang
 * @since 2018-10-16
 */
@Slf4j
@RestController
@RequestMapping("/data")
public class ImageDownloadController {
    
    @Autowired
    private DownloadGoogleImageService downloadGoogleImageService;
    
    @GetMapping(value = "/download/image/google", produces = "application/json;charset=utf-8")
    public R<String> downloadImage(@ModelAttribute ImageDownloadDTO imageDownloadDTO) {
        
        String savePath = imageDownloadDTO.getSavePath();
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }
        
        for (int zoom = imageDownloadDTO.getStartZoom(); zoom <= imageDownloadDTO.getEndZoom(); zoom++) {
            
            int[] start = GoogleImageUtil
                    .GoogleLonLatToXYZ(imageDownloadDTO.getStartLongitude(), imageDownloadDTO.getStartLatitude(), zoom);
            log.info("startLongitude[{}],startLatitude[{}],zoom[{}]对应的瓦块是x[{}],y[{}]",
                    imageDownloadDTO.getStartLongitude(), imageDownloadDTO.getStartLatitude(), zoom, start[0],
                    start[1]);
            
            int[] end = GoogleImageUtil
                    .GoogleLonLatToXYZ(imageDownloadDTO.getEndLongitude(), imageDownloadDTO.getEndLatitude(), zoom);
            log.info("endLongitude[{}],endLatitude[{}],zoom[{}]对应的瓦块是x[{}],y[{}]", imageDownloadDTO.getEndLongitude(),
                    imageDownloadDTO.getEndLatitude(), zoom, end[0], end[1]);
            
            ;
            for (int x = start[0]; x <= end[0]; x++) {
                StringBuilder path = new StringBuilder().append(savePath).append(zoom).append(File.separator).append(x)
                        .append(File.separator);
                for (int y = start[1]; y <= end[1]; y++) {
                    downloadGoogleImageService.downloadGoogleImage(x, y, zoom, path.toString());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        log.error("睡眠中断");
                    }
                }
            }
        }
        return R.ok();
    }
    
}
