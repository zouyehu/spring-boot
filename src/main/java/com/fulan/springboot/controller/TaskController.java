package com.fulan.springboot.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fulan.springboot.utils.FileUtils;

@Configuration
@EnableScheduling
public class TaskController {

	private static Log log = LogFactory.getLog(TaskController.class);

//	@Autowired
//	private RedisService redisService;

	@Autowired
	private FileUtils fileUtils;

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void setUser() {

		fileUtils.setFileUser();
	}
}
