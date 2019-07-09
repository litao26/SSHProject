package com.xuetao.service.impl;

import com.xuetao.dao.UserDao;
import com.xuetao.entity.User;
import com.xuetao.service.LoginService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LoginServiceImpl implements LoginService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(User user) {
        //System.out.println("用户名:" + user.getUsername());
        User resUser = userDao.getUser(user.getUsername(), user.getPassword());
        return resUser;
    }
}
