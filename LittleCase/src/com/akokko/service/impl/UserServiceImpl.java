package com.akokko.service.impl;

import com.akokko.dao.UserDao;
import com.akokko.dao.impl.UserDaoImpl;
import com.akokko.domain.PageBean;
import com.akokko.domain.User;
import com.akokko.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     * 验证登录用户
     * @param loginUser
     * @return
     */
    @Override
    public User checkLogin(User loginUser) {
        return dao.checkLogin(loginUser);
    }

    /**
     * 添加用户
     * @param addUser
     */
    @Override
    public void addUser(User addUser) {
        dao.addUser(addUser);
    }

    /**
     * 根据id删除用户
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {
        dao.deleteUser(Integer.parseInt(userId));
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Override
    public User findUser(String id) {
        return dao.findUser(Integer.parseInt(id));
    }

    /**
     * 根据传来的User对象修改数据库中的user数据
     * @param updateUser
     */
    @Override
    public void updateUser(User updateUser) {
        dao.updateUser(updateUser);
    }

    /**
     * 根据传来的ID数组删除对应的用户
     * @param ids
     */
    @Override
    public void deleteSelectedUsers(String[] ids) {
        for (String id : ids) {
            dao.deleteUser(Integer.parseInt(id));
        }
    }

    /**
     * 通过当前页码和每页数据数查找用户
     * @param currentPage
     * @param rows
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String currentPage, String rows) {
        int i_currentPage = Integer.parseInt(currentPage);
        int i_rows = Integer.parseInt(rows);

        PageBean<User> pb = new PageBean<>();
        pb.setRows(i_rows);

        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % i_rows == 0 ? totalCount / i_rows : totalCount / i_rows + 1;
        pb.setTotalPage(totalPage);

        if(i_currentPage < 1) {
            i_currentPage = 1;
        }

        if(i_currentPage > totalPage) {
            i_currentPage = totalPage;
        }

        pb.setCurrentPage(i_currentPage);

        int start = (i_currentPage - 1) * i_rows;
        List<User> users = dao.findUserByPage(start,i_rows);
        pb.setList(users);

        return pb;
    }

    /**
     * 通过条件、当前页码和每页数据数查找用户
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByConditionWithPage(String currentPage, String rows, Map<String, String[]> condition) {
        int i_currentPage = Integer.parseInt(currentPage);
        int i_rows = Integer.parseInt(rows);

        PageBean<User> pb = new PageBean<>();
        pb.setRows(i_rows);

        int totalCount = dao.findTotalCountByCondition(condition);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % i_rows == 0 ? totalCount / i_rows : totalCount / i_rows + 1;
        pb.setTotalPage(totalPage);

        if(i_currentPage < 1) {
            i_currentPage = 1;
        }

        if(i_currentPage > totalPage) {
            i_currentPage = totalPage;
        }

        pb.setCurrentPage(i_currentPage);

        int start = (i_currentPage - 1) * i_rows;
        List<User> users = dao.findUserByConditionWithPage(start,i_rows,condition);
        pb.setList(users);

        return pb;
    }
}
