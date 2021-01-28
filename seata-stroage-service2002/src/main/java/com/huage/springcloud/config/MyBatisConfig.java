package com.huage.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.huage.springcloud.dao")
public class MyBatisConfig {
}
