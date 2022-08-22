package com.akokko.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@PropertySource("classpath:druid.properties")  //加载配置文件
public class DataSourceConfiguration {

    //将配置文件的参数赋值给成员变量
    @Value("${driverClassName}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    @Bean("druidDataSource")  //设置该方法返回值给Spring容器
    public DataSource dataSourceConfiguration() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(user);
        druidDataSource.setPassword(password);
        //返回Druid连接池
        return druidDataSource;
    }
}
