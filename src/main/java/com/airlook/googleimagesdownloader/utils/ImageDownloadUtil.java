package com.airlook.googleimagesdownloader.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author: shenyonggang
 * @date: 2021-04-26 16:55
 */
@Slf4j
public class ImageDownloadUtil {
    
    public static final String CHARSET = "UTF-8";
    
    private static final CloseableHttpClient httpClient;
    
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(30000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }
    
    /**
     * 从网络上下载图片
     */
    public static boolean downloadPicture(String url, String dirPath, String filePath) {
        
        HttpGet httpget = new HttpGet(url);
        
        httpget.setHeader("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.128 Safari/537.36 Aoyou/UHsoRllGfDtyT2o4aDZwYVeQX-bIMksB1kxKyxoGcyWUvW5I3giKFKjufA==");
        httpget.setHeader("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        try {
            HttpResponse resp = httpClient.execute(httpget);
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                HttpEntity entity = resp.getEntity();
                
                InputStream in = entity.getContent();
                
                savePicToDisk(in, dirPath, filePath);
                
                log.info("保存图片 " + filePath + " 成功....");
                return true;
            } else if (HttpStatus.SC_BAD_REQUEST == resp.getStatusLine().getStatusCode()) {
                return false;
            }
        } catch (Exception e) {
            log.error("下载图片异常:{}", filePath, e);
        }
        return false;
    }
    
    /**
     * 将图片写到 硬盘指定目录下
     *
     * @param in
     * @param dirPath
     * @param filePath
     */
    private static void savePicToDisk(InputStream in, String dirPath, String filePath) {
        
        try {
            File dir = new File(dirPath);
            if (dir == null || !dir.exists()) {
                dir.mkdirs();
            }
            
            //文件真实路径
            String realPath = dirPath.concat(filePath);
            File file = new File(realPath);
            if (file == null || !file.exists()) {
                file.createNewFile();
            }
            
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            fos.close();
            
        } catch (IOException e) {
            log.error("保存图片异常:{}", filePath, e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                log.error("流关闭异常", e);
            }
        }
    }
}
