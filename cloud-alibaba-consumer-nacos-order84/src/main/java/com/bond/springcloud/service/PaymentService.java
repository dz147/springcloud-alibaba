package com.bond.springcloud.service;

import com.bond.springcloud.entities.Payment;
import com.bond.springcloud.model.ApiResult;
import com.bond.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Stephen
 * @date: 2022-01-25 11:27
 */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    ApiResult<Payment> paymentSQL(@PathVariable("id") Long id);

}
