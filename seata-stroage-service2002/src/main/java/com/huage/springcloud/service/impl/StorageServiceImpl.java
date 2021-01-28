package com.huage.springcloud.service.impl;

import com.huage.springcloud.dao.StorageDao;
import com.huage.springcloud.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
       // LOGGER.info("storage-service开始减库存");
        storageDao.decrease(productId,count);
       // LOGGER.info("storage-service结束减库存");
    }
}
