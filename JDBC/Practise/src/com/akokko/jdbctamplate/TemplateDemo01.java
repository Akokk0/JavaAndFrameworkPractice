package com.akokko.jdbctamplate;

import com.akokko.utils.DruidUtil;
import com.akokko.utils.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

public class TemplateDemo01 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(DruidUtil.getDataSource());
        String sql = "update user set username = '999' where id = 5";
        int i = template.update(sql);
        System.out.println(i);
    }
}
