package com.akokko.jdbc;

import com.akokko.utils.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class demo07 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DruidUtil.getConnection();

            String sql = "insert into user values(null,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"788");
            pstmt.setString(2,"788");

            int i = pstmt.executeUpdate();
            if (i > 0) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DruidUtil.close(pstmt,conn);
        }
    }
}
