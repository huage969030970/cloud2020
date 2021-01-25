package com.huage.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/testA")
    public String testA(){
        return serverPort + " testA  === ";
    }

    @GetMapping("/testB")
    public String testB(){

        return "  testB  === " + serverPort;
    }
    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("test D 测试");
        return "DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD";
    }
    @GetMapping("/testF")
    public String testF(){
        log.info("test info for FFFFFFFFFFFFFFFFFFF");
        int a = 10 / 0;
        return "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    }
    @GetMapping("/testE")
    public String testE(){
        log.info("test info for EEEEEEEEEEEEEEEEEEEEEEE");
        int a = 10 / 0;
        return "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "hotHandler")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "-------------------- testHotKey";
    }
    public String hotHandler(String p1, String p2, BlockException blockException) {
        return "hot handler!!!";
    }

}
