package com.bond.springcloud.service;


import com.bond.springcloud.domain.Order;

/**
 * @author stephen
 */
public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);

}
