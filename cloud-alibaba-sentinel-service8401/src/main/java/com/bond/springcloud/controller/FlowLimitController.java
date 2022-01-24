package com.bond.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.netty.util.Timeout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Stephen
 * @date: 2022-01-21 14:27
 */
@RestController
@RequestMapping("/sentinel")
@Slf4j
public class FlowLimitController {

    // @SentinelResource()
    @GetMapping("/testA")
    public String getA() {
        return "a";
    }

    @GetMapping("/testB")
    public String getB() {
        log.info("testB,{}", Thread.currentThread().getName());
        return "b";
    }

    @GetMapping("/testD")
    public String getD() {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // 异常比例
        int a = 10/0;
        log.info("testB,{}", Thread.currentThread().getName());
        return "d";
    }

    @GetMapping("/testE")
    public String getE() {
        int e = 10/0;
        log.info("testB,{}", Thread.currentThread().getName());
        return "e";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealTestHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2) {
        return "---testHotKey";
    }

    public String dealTestHotKey(String p1, String p2, BlockException exception){
        return "---testHotKey,/(ㄒoㄒ)/~~";
    }

}
