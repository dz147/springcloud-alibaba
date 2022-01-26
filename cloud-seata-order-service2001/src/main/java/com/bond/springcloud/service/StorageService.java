package com.bond.springcloud.service;

import com.bond.springcloud.model.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageService {

    /**
     * 扣减库存
     */
    @PostMapping(value = "/storage/decrease")
    ApiResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}


