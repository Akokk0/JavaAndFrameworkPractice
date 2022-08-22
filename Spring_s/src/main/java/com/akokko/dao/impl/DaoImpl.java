package com.akokko.dao.impl;

import com.akokko.dao.Dao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoImpl implements Dao {

    //从Spring容器中取出JdbcTemplate
    ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
    JdbcTemplate template = app.getBean(JdbcTemplate.class);

    @Override
    public void transfer() {
        String sql = "update account set money "

        template.update()
    }
}
