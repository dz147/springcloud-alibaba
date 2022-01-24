package com.bond.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.bond.springcloud.entities.Payment;
import com.bond.springcloud.model.ApiResult;
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
    private String serverUrl;

    @GetMapping(value = "/fallback/{id}", produces = {"application/json;charset=UTF-8"})
    private ApiResult<Payment> fallback(@PathVariable String id) {
        return restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, ApiResult.class, id);
    }

}
