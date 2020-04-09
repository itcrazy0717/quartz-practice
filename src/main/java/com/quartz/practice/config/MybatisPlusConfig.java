package com.quartz.practice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: dengxin.chen
 * @date: 2020-04-09 22:02
 * @description:
 */
@Configuration
@MapperScan("com.quartz.practice.dao")
public class MybatisPlusConfig {
}
