package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    /**
     * 通过用户名查找用户
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 通过Code查找对应用户
     * @param code
     * @return
     */
    User findUserByCode(String code);

    /**
     * 更改用户Status属性为Y
     * @param user
     */
    void updateStatus(User user);

    /**
     * 通过登录用户来查找真实用户
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username,String password);
}
