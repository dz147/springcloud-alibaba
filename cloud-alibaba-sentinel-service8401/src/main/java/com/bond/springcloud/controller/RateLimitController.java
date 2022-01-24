package com.bond.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bond.springcloud.entities.Payment;
import com.bond.springcloud.model.ApiResult;
import com.bond.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Stephen
 * @date: 2022-01-24 15:23
 */
@RestController
public class RateLimitController extends CommonResult {

    @GetMapping(value = "/byResource", produces = {"application/json;charset=UTF-8"})
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public ApiResult<Payment> byResource() {
        return success("按资源名称限流测试OK", new Payment(2022L, "serial001"));
    }

    public ApiResult<Payment> handleException(BlockException exception) {
        return failed(exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    /**
     * 流控按照默认返回兜底方法
     * */
    @GetMapping(value = "/rateLimit/byUrl",produces = { "application/json;charset=UTF-8" })
    @SentinelResource(value = "byUrl")
    public ApiResult<Payment> byUrl() {
        return success("按url限流测试OK", new Payment(2022L, "serial002"));
    }

    /**
     * 按照自定义返回兜底方法
     * */
    @GetMapping(value = "/rateLimit/customerBlockHandler",produces = { "application/json;charset=UTF-8" })
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException2")
    public ApiResult<Payment> customerBlockHandler() {
        return success("按客户自定义", new Payment(2022L, "serial003"));
    }
}

