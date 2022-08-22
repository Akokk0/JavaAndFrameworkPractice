package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {

    /**
     * 注册用户
     * @param regist
     * @return
     */
    boolean regist(User regist);

    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 通过登录用户来查找真实用户
     * @param loginUser
     * @return
     */
    User login(User loginUser);
}
