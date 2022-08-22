package com.akokko.dao.impl;

import com.akokko.dao.UserDao;
import com.akokko.domain.User;
import com.akokko.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过数据库查找所有用户
     * @return
     */
    @Override
    public List<User> findAll() {
        String sql = "select * from user";

        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    /**
     * 通过数据库查询传来的loginUser对象是否存在并返回User对象
     * @param loginUser
     * @return
     */
    @Override
    public User checkLogin(User loginUser) {
        String sql = "select * from user where username = ? and password = ?";

        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),loginUser.getUsername(),loginUser.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 通过传来的数据新建用户
     * @param addUser
     */
    @Override
    public void addUser(User addUser) {
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";

        template.update(sql,addUser.getName(),addUser.getGender(),addUser.getAge(),addUser.getAddress(),addUser.getQq(),addUser.getEmail());
    }

    /**
     * 通过传来的id删除对应的用户
     * @param id
     */
    @Override
    public void deleteUser(int id) {
        String sql = "delete from user where id = ?";

        template.update(sql,id);
    }

    /**
     * 通过传来的id查询数据库返回真正的User对象
     * @param id
     * @return
     */
    @Override
    public User findUser(int id) {
        String sql = "select * from user where id = ?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    /**
     * 通过传来的updateUser对象根据其ID进行修改
     * @param updateUser
     */
    @Override
    public void updateUser(User updateUser) {
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";

        template.update(sql,updateUser.getName(),updateUser.getGender(),updateUser.getAge(),updateUser.getAddress(),updateUser.getQq(),updateUser.getEmail(),updateUser.getId());
    }

    /**
     * 查找对应页码用户
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<User> findUserByPage(int start, int rows) {
        String sql = "select * from user limit ? , ?";

        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
        return users;
    }

    /**
     * 查找所有用户总数
     * @return
     */
    @Override
    public int findTotalCount() {
        String sql = "select count(*) from user";

        return template.queryForObject(sql, Integer.class);
    }

    /**
     * 通过条件查找所有用户总数
     * @param condition
     * @return
     */
    @Override
    public int findTotalCountByCondition(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 = 1";

        StringBuilder sb = new StringBuilder();
        sb.append(sql);

        List<Object> values = new ArrayList<>();
        for (String key : condition.keySet()) {
            if ("currentPage".equals(key) || "rows".equals(key)) continue;
            String value = condition.get(key)[0];

            if (value != null && !"".equals(value)) {
                values.add("%" + value + "%");
                sb.append(" and " + key + " like ?");
            }

        }
        sql = sb.toString();
        Integer TotalCountByCondition = template.queryForObject(sql, Integer.class,values.toArray());
        return TotalCountByCondition;
    }

    /**
     * 通过条件查找对应用户并用页面显示出来
     * @param start
     * @param i_rows
     * @param condition
     * @return
     */
    @Override
    public List<User> findUserByConditionWithPage(int start, int i_rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1";

        StringBuilder sb = new StringBuilder();
        sb.append(sql);

        List<Object> values = new ArrayList<>();
        for (String key : condition.keySet()) {
            if ("currentPage".equals(key) || "rows".equals(key)) continue;

            String value = condition.get(key)[0];
            if (value != null && !"".equals(value)) {
                values.add("%" + value + "%");
                sb.append(" and " + key + " like ?");
            }
        }
        sb.append(" limit ? , ?");
        sql = sb.toString();
        values.add(start);
        values.add(i_rows);

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), values.toArray());
    }
}
