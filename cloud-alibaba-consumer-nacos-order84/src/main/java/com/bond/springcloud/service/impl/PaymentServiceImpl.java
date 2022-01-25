package com.bond.springcloud.service.impl;

import com.bond.springcloud.entities.Payment;
import com.bond.springcloud.model.ApiErrorCode;
import com.bond.springcloud.model.ApiResult;
import com.bond.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @Author: Stephen
 * @date: 2022-01-25 11:29
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public ApiResult<Payment> paymentSQL(Long id) {
        return ApiResult.failed(ApiErrorCode.SPRING_BOOT_PLUS_EXCEPTION, "系统调用异常", null);
    }
}
