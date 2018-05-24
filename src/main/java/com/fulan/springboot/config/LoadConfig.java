package com.fulan.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

/**
 * 加载配置文件
 * 引入xml配置 @ImportResource("classpath:spring//-spring.xml")
 *
 * @auther Hu
 * @date 2018-05-22
 */
@Configuration
@PropertySource({"classpath:properties/config.properties"})
public class LoadConfig {
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }
}

