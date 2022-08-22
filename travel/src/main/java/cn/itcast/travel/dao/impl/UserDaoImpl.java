package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过用户名查找用户
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        //定义sql
        String sql = "select * from tab_user where username = ? ";
        //执行sql
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (DataAccessException e) {

        }
        return user;
    }

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void save(User user) {
        //定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?) ";
        //执行sql
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    /**
     * 通过Code查找对应用户
     * @param code
     * @return
     */
    @Override
    public User findUserByCode(String code) {
        //定义sql
        String sql = "select * from tab_user where code = ?";

        User user = null;
        try {
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (DataAccessException e) {

        }

        return user;
    }

    /**
     * 更改用户Status属性为Y
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        //定义sql
        String sql = "update tab_user set status = ? where uid =?";
        //执行sql
        template.update(sql,"Y",user.getUid());
    }

    /**
     * 通过登录用户来查找真实用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username,String password) {
        //定义sql
        String sql = "select * from tab_user where username = ? and password = ? ";
        //执行sql
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
        } catch (DataAccessException e) {

        }
        return user;
    }
}
