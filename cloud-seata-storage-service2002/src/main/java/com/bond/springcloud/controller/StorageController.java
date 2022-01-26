package com.bond.springcloud.controller;

import com.bond.springcloud.model.ApiResult;
import com.bond.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author Stephen Zhang
 * @Date 2022/1/26 21:56
 * @Version 1.0
 */
@RestController
@Slf4j
public class StorageController extends CommonResult {

    @Resource
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public ApiResult<Boolean> decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return success(Boolean.TRUE);
    }
}
