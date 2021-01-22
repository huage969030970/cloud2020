package com.huage.springcloud.controller;

import com.huage.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService hystrixService;

    @GetMapping("/consumer/payment/ok/{id}")
    public String test(@PathVariable("id") Integer id) {
        String s = hystrixService.testOk(id);
        log.info(s);
        return s;
    }

    @GetMapping("/consumer/payment/timeout/{id}")
    public String testTimeOut(@PathVariable("id") Integer id) {
        String s = hystrixService.testTimeOut(id);
        log.info(s);
        return s;
    }

    public String handlerTimeOut(Integer id) {
        return "消费者80端服务繁忙，请稍后再试！！";
    }
}
