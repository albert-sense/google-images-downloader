package com.airlook.googleimagesdownloader.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
public class ExecutorConfig {

	@Bean(value = "asyncServerExecutor")
	public Executor asyncServerExecutor() {
		log.info("init asyncServerExecutor thread pool");
		ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(5000);
		executor.setThreadNamePrefix("server-async-");
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// AbortPolicy：抛出异常
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		executor.initialize();
		return executor;
	}
}
