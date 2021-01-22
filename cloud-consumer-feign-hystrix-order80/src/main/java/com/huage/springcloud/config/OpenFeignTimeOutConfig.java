package com.huage.springcloud.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignTimeOutConfig {
    @Value("${feign.readTimeOut:15000}")
    private int readTimeOut;

    @Value("${feign,connectTimeOut:15000}")
    private int connectTimeOut;

    @Bean
    public Request.Options options(){
        return new Request.Options(connectTimeOut,readTimeOut);
    }
}
