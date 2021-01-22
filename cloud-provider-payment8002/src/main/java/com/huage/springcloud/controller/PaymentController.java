package com.huage.springcloud.controller;

import com.huage.springcloud.entities.CommonResult;
import com.huage.springcloud.entities.Payment;
import com.huage.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/save")
    public CommonResult<?> save(@RequestBody Payment payment) {
        Integer result = paymentService.create(payment);

        if(result >= 1) return new CommonResult<Integer>(200,"插入数据成功",result);
        else return new CommonResult<>(444, "插入数据失败！",null);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<?> get(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);

        log.info(payment.toString() );
        log.info(serverPort);

        if(!StringUtils.isEmpty(payment)) {
            return new CommonResult<>(200,"查询成功！",payment);
        }else {
            return new CommonResult<>(444,"查询失败！");
        }
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/discovery")
    public DiscoveryClient getDiscoveryClient(){
        List<String> services = discoveryClient.getServices();
        for (String str:services) {
            log.info(str + " \t\t");
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId() + " " + instance.getHost() + " " + instance.getPort() + " " + instance.getUri());
        }


        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String testlb() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String timeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
