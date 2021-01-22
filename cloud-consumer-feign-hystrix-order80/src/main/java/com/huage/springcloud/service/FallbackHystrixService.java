package com.huage.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackHystrixService implements PaymentHystrixService{

    @Override
    public String testOk(Integer id) {
        return "OK服务出错了，哭了！！";
    }

    @Override
    public String testTimeOut(Integer id) {
        return "TimeOut服务调用出错！！";
    }
}
