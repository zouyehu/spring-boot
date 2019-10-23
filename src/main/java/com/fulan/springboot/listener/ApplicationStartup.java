package com.fulan.springboot.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartup implements ApplicationListener {

    private static Log log = LogFactory.getLog(ApplicationStartup.class);

 
    
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

    	//启动监听,do
//    	log.info("这是监听启动时要做的事情");
    }
}
