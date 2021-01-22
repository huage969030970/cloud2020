package com.huage.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "globalFallBack",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
})
public class PaymentService {

    @HystrixCommand(fallbackMethod = "circuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled" , value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "60")
    })
    public String payment_OK(Integer id) {
        if(id < 0) {
            throw new RuntimeException("id不能为负数！！");
        }
        return "线程池：" + Thread.currentThread().getName() + "payment is OK : id = " + id;
    }

    public String circuitBreakerFallback(Integer id) {
        return Thread.currentThread().getName() + " 调用了熔断方法！！！";
    }

    @HystrixCommand
    public String payment_TimeOut(Integer id) {
        int time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "time out  = " + time + " 秒";
    }

    public String paymentHandlerTimeOut(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 服务繁忙，请稍后再试。 id = " + id;
    }
    public String globalFallBack() {
        return "线程池：" + Thread.currentThread().getName() + " 服务繁忙，请稍后再试";
    }
}
