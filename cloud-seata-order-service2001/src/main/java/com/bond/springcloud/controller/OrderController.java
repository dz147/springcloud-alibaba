package com.bond.springcloud.controller;

import com.bond.springcloud.domain.Order;
import com.bond.springcloud.model.ApiResult;
import com.bond.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController extends CommonResult {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public ApiResult<Boolean> create(Order order) {
        orderService.create(order);
        return success(true);
    }
}
