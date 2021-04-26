package com.airlook.googleimagesdownloader.service.impl;

import com.airlook.googleimagesdownloader.service.DownloadGoogleImageService;
import com.airlook.googleimagesdownloader.utils.ImageDownloadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: shenyonggang
 * @date: 2021-04-22 08:55
 */
@Slf4j
@Service
public class DownloadGoogleImageServiceImpl implements DownloadGoogleImageService {
    
    public List<String> gooleImageUrls = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        gooleImageUrls.add("https://mt0.google.cn/maps/vt?lyrs=s%40189&x={x}&y={y}&z={z}");
        gooleImageUrls.add("https://mt1.google.cn/maps/vt?lyrs=s%40189&x={x}&y={y}&z={z}");
        gooleImageUrls.add("https://mt2.google.cn/maps/vt?lyrs=s%40189&x={x}&y={y}&z={z}");
        gooleImageUrls.add("https://mt3.google.cn/maps/vt?lyrs=s%40189&x={x}&y={y}&z={z}");
    }
    
    @Async("asyncServerExecutor")
    @Override
    public void downloadGoogleImage(int x, int y, int zoom, String savePath) {
        String url = gooleImageUrls.get(random());
        url = url.replace("{x}", x + "").replace("{y}", y + "").replace("{z}", zoom + "");
        ImageDownloadUtil.downloadPicture(url, savePath, y + ".jpg");
    }
    
    public int random() {
        return (int) (Math.random() * (4 - 0) + 0);
    }
}
