package com.airlook.googleimagesdownloader.service;

public interface DownloadGoogleImageService {
    
    /**
     * 下载影像
     *
     * @param x
     * @param y
     * @param zoom
     * @param savePath
     */
    void downloadGoogleImage(int x, int y, int zoom, String savePath);
}
