package com.huage.springcloud.service.impl;

import com.huage.springcloud.dao.OrderDao;
import com.huage.springcloud.domain.Order;
import com.huage.springcloud.service.AccountService;
import com.huage.springcloud.service.OrderService;
import com.huage.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;

    @GlobalTransactional
    @Override
    public void create(Order order) {
        log.info("-------------->开始创建订单");

        orderDao.create(order);

        log.info("-------------->微服务，减库存start");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("-------------->微服务，减库存end");

        log.info("-------------->微服务，减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("-------------->微服务，减钱end");

        log.info("-------------->更新订单状态start");
        orderDao.update(order.getUserId(),0);
        log.info("-------------->更新订单状态end");
    }
}
