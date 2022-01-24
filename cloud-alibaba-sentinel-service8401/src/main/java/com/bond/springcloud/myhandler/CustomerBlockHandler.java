package com.bond.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bond.springcloud.model.ApiResult;

/**
 * @Author: Stephen
 * @date: 2022-01-24 16:31
 */
public class CustomerBlockHandler {
    public static ApiResult<Object> handlerException(BlockException exception) {
        return new ApiResult<>().setCode(444L).setMsg("自定义全局限流---1");
    }

    public static ApiResult<Object> handlerException2(BlockException exception) {
        return new ApiResult<>().setCode(444L).setMsg("自定义全局限流---2");
    }
}
