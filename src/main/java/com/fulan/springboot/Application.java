package com.fulan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.fulan"})
public class Application {

    public static void main(String[] args) {
        //添加启动监听程序
//    	SpringApplication springApplication = new SpringApplication(Application.class);
//    	springApplication.addListeners(new ApplicationStartup());
//    	springApplication.run(args);

        SpringApplication.run(Application.class, args);
    }
}
