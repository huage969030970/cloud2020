package com.huage.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huage.springcloud.entities.CommonResult;

public class ConsumerBlockHandler {

    public static CommonResult<?> handlerException(BlockException exception) {
        return new CommonResult<>(2020,"----自定义默认兜底方法");
    }
}
