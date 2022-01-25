package com.bond.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.bond.springcloud.entities.Payment;
import com.bond.springcloud.model.ApiErrorCode;
import com.bond.springcloud.model.ApiResult;
import com.bond.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author: Stephen
 * @date: 2022-01-18 16:17
 */
@RestController
@RequestMapping("/consumer")
public class CircleBreakerController extends CommonResult {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    public String serverUrl;

    /**
     * 注解@SentinelResource 属性 fallback管运行时,blockHandler管配置违规
     * exceptionsToIgnore = IllegalArgumentException.class
     *
     * @SentinelResource("fallback") 无配置
     */
    @GetMapping(value = "/fallback/{id}", produces = {"application/json;charset=UTF-8"})
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    public ApiResult<Payment> fallback(@PathVariable Long id) {
        ApiResult<Payment> result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id,
                ApiResult.class, id);
        long val = 4L;
        if (id == val) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result == null || result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    public ApiResult<Object> handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment();
        payment.setId(id);
        return failed(ApiErrorCode.BUSINESS_EXCEPTION, "Java异常熔断兜底方法" + e.getMessage(), payment);
    }

    public ApiResult<Object> blockHandler(@PathVariable Long id, BlockException e) {
        return failed(ApiErrorCode.BUSINESS_EXCEPTION, "控制台兜底方法" + e.getMessage(), null);
    }

    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/openFeign/paymentSQL/{id}", produces = {"application/json;charset=UTF-8"})
    public ApiResult<Payment> getPayment(@PathVariable Long id) {
        return paymentService.paymentSQL(id);
    }

}
