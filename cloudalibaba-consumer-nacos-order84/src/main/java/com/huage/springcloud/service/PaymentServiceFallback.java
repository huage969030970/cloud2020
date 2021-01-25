package com.huage.springcloud.service;

import com.huage.springcloud.entities.CommonResult;
import com.huage.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService{
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"OpenFeign   fallback!!");
    }
}
