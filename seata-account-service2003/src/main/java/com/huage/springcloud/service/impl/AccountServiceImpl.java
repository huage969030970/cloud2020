package com.huage.springcloud.service.impl;

import com.huage.springcloud.dao.AccountDao;
import com.huage.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("account-service开始扣账号余额");
        accountDao.decrease(userId,money);
        log.info("account-service结束扣账号余额");
    }
}
