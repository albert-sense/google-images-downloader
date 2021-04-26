package com.airlook.googleimagesdownloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class GoogleImagesDownloaderApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GoogleImagesDownloaderApplication.class, args);
    }
    
}
