package com.fulan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fulan.springboot.listener.ApplicationStartup;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	//添加启动监听程序
//    	SpringApplication springApplication = new SpringApplication(Application.class);
//    	springApplication.addListeners(new ApplicationStartup());
//    	springApplication.run(args);
    	
        SpringApplication.run(Application.class, args);
    }
}
