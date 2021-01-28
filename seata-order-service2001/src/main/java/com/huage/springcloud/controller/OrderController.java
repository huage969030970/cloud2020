package com.huage.springcloud.controller;

import com.huage.springcloud.domain.CommonResult;
import com.huage.springcloud.domain.Order;
import com.huage.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult<?> create(Order order) {
        System.out.println(order);
        orderService.create(order);
        return new CommonResult<>(200,"订单创建成功！");
    }
}
