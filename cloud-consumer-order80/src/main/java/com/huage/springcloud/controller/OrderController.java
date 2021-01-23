package com.huage.springcloud.controller;

import com.huage.springcloud.entities.CommonResult;
import com.huage.springcloud.entities.Payment;
import com.huage.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    @PostMapping("/payment/save")
    public CommonResult<?> save(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save",payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<?> get(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping("/payment/lb")
    public String testing(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");


        if(instances == null || instances.size() == 0) return null;

        ServiceInstance instance = loadBalancer.instances(instances);
        URI uri = instance.getUri();
        String res = restTemplate.getForObject(uri + "/payment/lb" ,String.class);
        log.info(res);
        return res;
    }

    @GetMapping("/payment/zipkin")
    public String testZipkin() {
        return restTemplate.getForObject("http://localhost:8001/payment/zipkin",String.class);
    }

}
