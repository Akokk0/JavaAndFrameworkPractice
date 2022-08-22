package com.akokko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration  //Spring核心注解配置类
@ComponentScan("com.akokko")  //Spring扫描此目录下的注解
@Import(DataSourceConfiguration.class)
public class SpringConfiguration {
}
