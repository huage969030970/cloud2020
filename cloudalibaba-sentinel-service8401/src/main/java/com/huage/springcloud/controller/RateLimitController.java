package com.huage.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huage.springcloud.entities.CommonResult;
import com.huage.springcloud.entities.Payment;
import com.huage.springcloud.myhandler.ConsumerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "byResourceHandler")
    public CommonResult<?> byResource(){
        return new CommonResult(200,"资源请求成功",new Payment(111L,"testByResource"));
    }

    public CommonResult<?> byResourceHandler(BlockException exception) {
        return new CommonResult<>(444,exception.getClass().getCanonicalName()+ "服务不可用");
    }

    @GetMapping("/retaLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public String byUrl(){
        return "testtttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
    }

    @GetMapping("/rateLimit/consumerBlock")
    @SentinelResource(  value = "consumerBlock",
                        blockHandlerClass = ConsumerBlockHandler.class,
                        blockHandler = "handlerException")
    public CommonResult<?> consumerBlock() {
        return new CommonResult<>(200,"success",new Payment(2020L,"test"));
    }
}
