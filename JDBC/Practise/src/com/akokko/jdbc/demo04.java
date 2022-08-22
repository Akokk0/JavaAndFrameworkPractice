package com.akokko.jdbc;

import com.akokko.domain.stu;
import com.akokko.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class demo04 {

    public static void main(String[] args) {
        List<stu> list = new demo04().findAll();
        //System.out.println(list);
        for (stu stu:list) {
            System.out.println(stu);
        }
    }

    public List<stu> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<stu> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM stu";
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);

                stu stu = new stu(id,name,age);
                list.add(stu);
            }
            //System.out.println(list);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(stmt,conn,rs);
        }


        return list;
    }
}
