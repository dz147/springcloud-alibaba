package com.bond.springcloud.controller;

import com.bond.springcloud.entities.Payment;
import com.bond.springcloud.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author: Stephen
 * @date: 2022-01-24 17:17
 */
@RestController
@Slf4j
public class PaymentController extends CommonResult {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L, new Payment(2L, "bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L, new Payment(3L, "6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public ApiResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return success("from mysql,serverPort:  " + serverPort, hashMap.get(id));
    }
}
