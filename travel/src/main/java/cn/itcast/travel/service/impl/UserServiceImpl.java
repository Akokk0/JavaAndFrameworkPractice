package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    //创建Dao对象
    private UserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     * @param regist
     * @return
     */
    @Override
    public boolean regist(User regist) {

        //调用Dao中findUserByUsername方法查找是否已经存在用户
        User user = dao.findUserByUsername(regist.getUsername());

        //判断User对象是否为空，若为空则不存在,不为空，用户存在，注册失败
        if (user != null) return false;

        //用户不存在,进行下面操作
        //将registUser对象的status值设置为'N'
        regist.setStatus("N");

        //获取UUID
        regist.setCode(UuidUtil.getUuid());

        //发送激活邮件
        MailUtils.sendMail(regist.getEmail(),"<a href='http://localhost/travel/activeServlet?code=" + regist.getCode() + "'>点击链接激活账号【PCG Game Union】</a>","【PCG Game Union】");

        //调用Dao中save方法
        dao.save(regist);
        return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //调用dao中findUserByCode方法找到对应用户
        User user = dao.findUserByCode(code);

        //判断user对象是否存在，若不存在则返回false
        if (user == null) return false;

        //user对象存在，则调用dao中updateStatus方法，将User对象中Status值改为'Y'
        dao.updateStatus(user);

        //激活成功,返回true
        return true;
    }

    /**
     * 通过登录用户来查找真实用户
     * @param loginUser
     * @return
     */
    @Override
    public User login(User loginUser) {
        return dao.findUserByUsernameAndPassword(loginUser.getUsername(),loginUser.getPassword());
    }
}
