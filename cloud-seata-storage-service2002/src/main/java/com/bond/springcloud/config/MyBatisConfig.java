package com.bond.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author stephen
 */
@Configuration
@MapperScan({"com.bond.springcloud.dao"})
public class MyBatisConfig {
}
