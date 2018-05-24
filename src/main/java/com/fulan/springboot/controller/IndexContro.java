package com.fulan.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class IndexContro {
    private static final Logger logger = LoggerFactory.getLogger(IndexContro.class);

    @Value("${fulan.name}")
    private String fulan_name;
    @Value("${fulan.address}")
    private String fulan_address;

    @GetMapping("index")
    public String index(){
        logger.info("---进入首页");
        logger.error("姓名：" + fulan_name + "地址：" + fulan_address);
        return "index";
    }

    @GetMapping("login.html")
    public String login(){
        logger.info("---进入登录页");
        return "login";
    }
}
