package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务配置
* 
* 项目名称:  springCloud0
* 包:       com.example.demo.config   
* 类名称:    SchedulingConfig.java
* 类描述:    
* 创建人:    yzx 
* 创建时间:  2017年10月25日
 */
@Configuration
@EnableScheduling
public class SchedulingConfig {

	private static final Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);
	
	@Scheduled(cron="0/20 * * * * ?")
	public void scheduler() {
		logger.info("----------------------scheduler on :{}",System.currentTimeMillis());
	}
}
